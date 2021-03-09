<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House >회원탈퇴</h2>
        </div>
      </div>
    </section>
    <div class="text-center">
      <br />
      <br />
      <br />

      <h2>탈퇴를 원하신다면 비밀번호를 입력하여 주세요.</h2>
      <br />

      <input type="password" v-model="password" style="border:1px solid black" />
      <br />
      <br />

      <button class="btn btn-primary" @click="withdraw()">회원탈퇴</button>
      <br />
      <br />
      <br />
      <br />
    </div>
  </div>
</template>

<script>
import axios from "axios";
const storage = window.sessionStorage;
export default {
  data() {
    return {
      password: ""
    };
  },
  methods: {
    withdraw() {
      let user = JSON.parse(sessionStorage.getItem("login_user"));
      if (user.password == this.password) {
        axios({
          method: "delete",
          url: "http://localhost:8080/api/user/withdraw/" + user.id
        })
          .then(res => {
            if (res.data.status) {
              alert("회원탈퇴 성공 메인 페이지로 이동 합니다.");
              storage.setItem("jwt-auth-token", "");
              storage.setItem("login_user", "");
              this.$store.dispatch("setLoginId", "");
              this.$router.push("/");
            }
          })
          .catch(error => {
            console.dir(error);
            alert("회원탈퇴에 문제 발생 다시 한번 확인해주세요.");
          });
      } else {
        alert("비밀번호를 다시 확인해주세요.");
      }
    }
  }
};
</script>

<style></style>
