<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 비밀번호 찾기</h2>
        </div>
      </div>
    </section>
    <div class="container" align="center" style="width: 400px">
      <br />
      <h3>비밀번호 찾기</h3>
      <form action method="post">
        <div>
          <input
            class="form-control"
            type="text"
            id="id"
            name="id"
            ref="id"
            v-model="id"
            placeholder="아이디를 입력하세요."
          />
          <br />
          <input
            class="form-control"
            type="text"
            id="name"
            name="name"
            ref="name"
            v-model="name"
            placeholder="이름을 입력하세요."
          />
          <br />
          <input type="submit" value="찾기" @click.prevent="checkHandler()" />
          <br />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      id: "",
      name: ""
    };
  },
  methods: {
    checkValid(data, msg, elem) {
      if (!data) {
        alert(msg);
        elem.focus();
        return false;
      }
      return true;
    },
    checkHandler() {
      // 사용자 입력값 체크하기
      let valid = this.checkValid(this.id, "ID를 입력하세요", this.$refs.id);
      if (valid) {
        valid = this.checkValid(
          this.name,
          "이름을 입력하세요",
          this.$refs.name
        );
      }
      // 만약, 내용이 다 입력되어 있다면 createHandler 호출
      if (valid) {
        this.findPwd();
      }
    },
    findPwd() {
      axios({
        method: "get",
        url:
          "http://localhost:8080/api/user/findpwd?id=" +
          this.id +
          "&" +
          "name=" +
          this.name
        // data: {
        //   id: this.id,
        //   name: this.name
        // }
      })
        .then(data => {
          console.dir(data);
          let msg = "정확한 값을 입력해주세요.";
          if (data.data.status) {
            if (data.data.data != null) {
              msg = "회원님의 비밀번호는 '" + data.data.data + "' 입니다";
              this.$router.push("/");
            }
            alert(msg);
          }
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>

<style></style>
