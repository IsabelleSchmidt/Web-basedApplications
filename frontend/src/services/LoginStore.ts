import { computed, reactive } from "vue";
import '../service/Requests'

const jwtToken1: JwtToken = {username: "", accessToken: "" };

const loginstate = reactive({
    
    errormessage: "",
    
    username: "",
   
    isLoggedIn: false,

    jwttoken:  jwtToken1
    
    // errormessages: Array<MessageResponse>(),
    
    // allAdresses: Array<Adress>(),
    
    // user : Array<User>()
})

function doLogout(){
    loginstate.jwttoken = "";
    // loginstate.jwttoken.email = "";
    // state.jwttoken.roles = [];
    loginstate.errormessage = "";
    loginstate.username = "";
    loginstate.isLoggedIn = false;
}

async function doLogin(LoginRequest: LoginRequest): Promise<boolean> {
   
    loginstate.isLoggedIn = false;
    await fetch(`/api/login`, {
        method: 'POST',
        headers: { "Content-Type": 'application/json'},
        body: JSON.stringify(LoginRequest),
    }).then((response) => {
        if(!response.ok){
            doLogout();
            throw new Error(loginstate.errormessage);
            
        }else{
            loginstate.username = LoginRequest.username;
            loginstate.jwttoken = LoginRequest;
            loginstate.isLoggedIn = true;
            loginstate.errormessage = "";
        }
    }).then((jsondata: JwtToken) => {
            loginstate.jwttoken = jsondata;
    }).catch(() => {
            loginstate.errormessage = "Email-Adresse oder Passwort falsch."
    })
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

