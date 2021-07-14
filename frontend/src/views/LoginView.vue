<template>
  <div class="login">
    <h1 align="center">Login</h1>
    <p id="error" align="center">{{ errormessage }}</p>
    <form @submit.prevent="loginUser()">
      <div class="row">
        <div class="col1">
          <label for="username" class="left">Username</label>
        </div>
        <div class="col2">
          <input
            v-model="username"
            id="username"
            type="text"
            name="username"
            size="30"
            maxlenght="50"
            class="right"
            style="border:1px solid grey"
          />
        </div>
      </div>
      <div class="row">
        <div class="col1">
          <label for="password" class="left">Passwort</label>
        </div>
        <div class="col2">
          <input
            v-model="password"
            id="pw"
            type="password"
            name="password"
            size="20"
            maxlenght="50"
            class="right"
            style="border:1px solid grey"
          />
        </div>
      </div>
      <input type="submit" name="doLogin" value="Login" >
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import {useLoginStore} from "../services/LoginStore"
import { useRouter } from "vue-router";
// import '../services/Requests'

export default defineComponent({
    name: "LoginView",

    setup(){

        const username = ref("");
        const password = ref("");
        const router = useRouter();

        // const loginRequest: lo = {
        //     username: usern.value,
        //     password: passw.value
        // };
    
        const { doLogout, doLogin} = useLoginStore();
    
        onMounted(async () => {
            doLogout();
        });

        async function loginUser(){
            const loginSuccess = await doLogin(username.value, password.value);

            if(loginSuccess){
                router.push('/');
            }else{
                router.push('/login')
            }
        }

        return{
            loginUser,
            username,
            doLogin,
            password,
            doLogout
        }
    }
})

</script>