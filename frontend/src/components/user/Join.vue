<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 회원가입</h2>
        </div>
      </div>
    </section>
    <div class="container" align="center" style="width: 400px">
      <div class="col_mypage" align="center">
        <form id="joinform" action method="post">
          <div class="form-group" align="right">
            <label style="color: red">(*)필수입력항목</label>
          </div>
          <div class="form-group" align="left">
            <label>아이디*</label>
            <input class="form-control" type="text" id="id" ref="id" v-model="id" />
          </div>
          <div class="form-group" align="left">
            <label>비밀번호*</label>
            <input
              class="form-control"
              type="password"
              id="password"
              ref="password"
              v-model="password"
            />
          </div>
          <div class="form-group" align="left">
            <label>이름*</label>
            <input class="form-control" type="text" id="name" ref="name" v-model="name" />
          </div>
          <div class="form-group" align="left">
            <label>이메일</label>
            <input class="form-control" type="text" id="email" name="email" v-model="email" />
          </div>
          <div class="form-group" align="left">
            <label>주소</label>
            <input class="form-control" type="text" id="address" name="address" v-model="address" />
          </div>
          <div class="form-group" align="left">
            <label>전화번호</label>
            <input class="form-control" type="text" id="phone" name="phone" v-model="phone" />
          </div>
          <button
            type="button"
            class="btn btn-success"
            @click.prevent="checkHandler()"
            style="color:white; width:150px"
          >회원가입</button>
          <br />
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      id: "",
      password: "",
      name: "",
      email: "",
      address: "",
      phone: "",
      regiDate: "",
      grade: ""
      //  attention: ""
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
          this.password,
          "비밀번호를 입력하세요",
          this.$refs.password
        );
      }
      if (valid) {
        valid = this.checkValid(
          this.name,
          "이름을 입력하세요",
          this.$refs.name
        );
      }

      // 만약, 내용이 다 입력되어 있다면 createHandler 호출
      if (valid) {
        this.join();
      }
    },
    join() {
      axios({
        method: "post",
        url: "http://localhost:8080/api/user/join",
        data: {
          id: this.id,
          password: this.password,
          name: this.name,
          email: this.email,
          address: this.address,
          phone: this.phone,
          regiDate: this.regiDate,
          grade: this.grade
          //  attention: this.attention
        }
      })
        .then(data => {
          console.dir(data);
          let msg = "회원 가입중 문제가 발생";
          if (data.status) {
            msg = "회원 가입 완료";
          }
          alert(msg);
          this.$router.push("/joinsuccess");
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>

<style></style>
