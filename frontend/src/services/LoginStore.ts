import { computed, reactive } from "vue";
import '../service/Requests'

const loginstate = reactive({
    
    errormessage: "",
    
    username: "",
   
    isLoggedIn: false,

    jwttoken:  ""
    
})

function doLogout(){
    loginstate.jwttoken = "";
    loginstate.errormessage = "";
    loginstate.username = "";
    loginstate.isLoggedIn = false;
}

async function doLogin(LoginRequest: LoginRequest): Promise<boolean> {
   
    loginstate.isLoggedIn = false;
    await fetch(`/api/login`, {
        method: 'POST',
        headers: { "Content-Type": 'application/json',
                Authorization: "Bearer" + loginstate.jwttoken},
        body: JSON.stringify(LoginRequest)
    }).then((response) => response.text() 
    ).then((res) => {
        loginstate.errormessage = "";
        loginstate.isLoggedIn = true;
        loginstate.jwttoken = String(res);
        loginstate.username = LoginRequest.username;
    //     if(!response.ok){
    //         doLogout();
    //         throw new Error(loginstate.errormessage);
            
    //     }else{
    //         loginstate.username = LoginRequest.username;
    //         // loginstate.jwttoken = LoginRequest.;
    //         loginstate.isLoggedIn = true;
    //         loginstate.errormessage = "";
    //     }
    // }).then((jsondata: String) => {
    //         loginstate.jwttoken = jsondata.toString;
    }).catch((e) => {
            doLogout();
            loginstate.errormessage = e;
    });
    return loginstate.isLoggedIn;
}

export function useLoginStore() {
    return {
        jwttoken: computed(() => loginstate.jwttoken),
        username: computed(() => loginstate.username),
        errormessage: computed(() => loginstate.errormessage),
        isLoggedIn: computed(() => loginstate.isLoggedIn),
        doLogin,
        doLogout
    }
}

