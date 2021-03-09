<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 마이페이지</h2>
        </div>
      </div>
    </section>
    <div class="container" align="center" style="width: 400px">
      <h5 style="color: blue"></h5>
      <div class="col_mypage" align="center">
        <form id="mypageform" action method="post">
          <div class="form-group" align="left">
            <label>아이디</label>
            <input class="form-control" type="text" id="id" name="id" value disabled v-model="id" />
          </div>
          <div class="form-group" align="left">
            <label>비밀번호</label>
            <input
              class="form-control"
              type="text"
              id="password"
              name="password"
              ref="password"
              v-model="password"
            />
          </div>
          <div class="form-group" align="left">
            <label>이름</label>
            <input class="form-control" type="text" id="name" name="name" ref="name" v-model="name" />
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

          <div class="form-group" align="left">
            <label>가입일자</label>
            <input class="form-control" type="text" value disabled v-model="regiDate" />
          </div>
          <div>
            <button
              type="button"
              class="btn btn-primary"
              @click.prevent="modify()"
              style="color:white"
            >수정</button>
            <button
              type="button"
              class="btn btn-danger"
              @click.prevent="cancel()"
              style="color:white"
            >취소</button>
          </div>
          <div>
            <a href="#" style="color:black" @click="movewithdraw">회원탈퇴</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const storage = window.sessionStorage;
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
      // attention: ""
    };
  },
  created() {
    let user = JSON.parse(sessionStorage.getItem("login_user"));
    this.id = user.id;
    this.password = user.password;
    this.name = user.name;
    this.email = user.email;
    this.address = user.address;
    this.phone = user.phone;
    this.regiDate = user.regidate;
    //this.attention = user.attention;
  },
  methods: {
    movewithdraw() {
      this.$router.push("/withdraw");
    },
    modify() {
      axios({
        method: "put",
        url: "http://localhost:8080/api/user/modify/",
        data: {
          id: this.id,
          password: this.password,
          name: this.name,
          email: this.email,
          address: this.address,
          phone: this.phone,
          regiDate: this.regiDate,
          grade: this.grade
          // attention: this.attention
        }
      })
        .then(res => {
          if (res.data.status) {
            // 현재 session storage에 있는 값들을 업데이트 시켜줘야 한다.
            let user = {
              id: this.id,
              password: this.password,
              email: this.email,
              name: this.name,
              address: this.address,
              regidate: this.regidate,
              phone: this.phone,
              grade: this.grade
              // attention: this.attention
            };
            storage.setItem("login_user", JSON.stringify(user));
            alert("유저 데이터 수정 성공 메인페이지로 이동합니다.");
            this.$router.push("/");
          }
        })
        .catch(error => {
          console.dir(error);
          alert("유저 데이터 수정중 문제 발생 다시 시도해주세요.");
        });
    },
    checkModify() {
      let err = true;
      let msg = "";
      !this.password &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.password.focus());
      err &&
        !this.name &&
        ((msg = "이름을 입력해주세요"), (err = false), this.$refs.name.focus());

      if (!err) alert(msg);
      else this.modify();
    },
    cancel() {
      this.$router.push("/");
    }
  }
};
</script>

<style></style>
