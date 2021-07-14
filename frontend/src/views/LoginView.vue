<template>
  <div class="login">
    <h1 align="center">Login</h1>
    <p id="error" align="center">{{ errormessage }}</p>
    <form @submit.prevent="loginUser()">
      <div class="row">
        <div class="col1">
          <label for="email" class="left">E-Mail Adresse</label>
        </div>
        <div class="col2">
          <input
            v-model="email"
            id="email"
            type="text"
            name="email"
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
import Vue, { defineComponent, onMounted, ref } from 'vue'
import {useLoginStore} from "../services/LoginStore"
import { useRouter } from "vue-router";
import '../services/Requests'

export default defineComponent({
    name: "LoginView",

    setup(props, context){

        const username = ref("");
    
        const password = ref("");
        const router = useRouter();

        const loginRequest: LoginRequest = {
            username: username.value,
            password: password.value
        };
    
        const { doLogout, doLogin } = useLoginStore();
    
        onMounted(async () => {
            doLogout();
        });

        async function loginUser(){
            loginRequest.username = username.value;
            loginRequest.password = password.value;
            const loginSuccess = await doLogin(loginRequest);

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
            doLogout,
            loginRequest
        }
    }
})

</script>