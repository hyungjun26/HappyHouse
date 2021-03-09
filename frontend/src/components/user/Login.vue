<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 로그인</h2>
        </div>
      </div>
    </section>
    <div align="center"></div>
    <div class="container" align="center" style="width: 400px">
      <div class="col_login" align="center">
        <form id="loginform" action method="post">
          <div class="form-group" align="left">
            <label>아이디</label>
            <input class="form-control" type="text" id="id" name="id" v-model="id" placeholder />
          </div>
          <div class="form-group" align="left">
            <label>비밀번호</label>
            <input
              class="form-control"
              type="password"
              id="password"
              name="password"
              v-model="password"
              placeholder
            />
          </div>
          <div class="form-group" align="center">
            <button
              type="button"
              class="btn btn-info"
              id="login"
              @click.prevent="login"
              style="color:white; width:150px"
            >로그인</button>
            <!-- <button type="button" class="btn btn-success" @click="moveJoin">회원가입</button> -->
          </div>
          <div class="form-group" align="center">
            <a href="#" style="color:black" @click="moveJoin">회원가입</a>
            <br />
            <a href="#" style="color:black" @click.prevent="moveFind">비밀번호찾기</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const storage = window.sessionStorage;

const ai = axios.create({
  baseURL: "http://localhost:8080/api/"
});
export default {
  name: "Login",
  data() {
    return {
      id: "",
      password: "",
      name: "",
      email: "",
      address: "",
      phone: "",
      regiDate: "",
      grade: "",
    //  attention: "",
      message: "로그인 해주세요"
    };
  },
  methods: {
    moveFind() {
      this.$router.push("/findpwd");
    },
    moveJoin() {
      this.$router.push("/join");
    },
    setInfo(status, token, info) {
      this.status = status;
      this.token = token;
      this.info = info;
    },
    logout() {
      storage.setItem("jwt-auth-token", "");
      storage.setItem("login_user", " ");
      this.message = "로그인 해주세요.";
      this.setInfo("로그아웃 성공", "", "");
    },
    getInfo() {
      ai.post(
        "/info",
        {
          id: "some id",
          password: "some password"
        },
        {
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token")
          }
        }
      )
        .then(res => {
          this.setInfo(
            "정보 조회 성공",
            res.headers.auth_token,
            JSON.stringify(res.data)
          );
        })
        .catch(e => {
          this.setInfo("정보 조회 실패", "", e.response.data.msg);
        });
    },
    login() {
      storage.setItem("jwt-auth-token", "");
      storage.setItem("login_user", "");
      ai.post("/user/signin", {
        id: this.id,
        password: this.password
      })
        .then(res => {
          if (res.data.status) {
            //console.dir(res);
            this.message = res.data.data.id + "로 로그인 되었습니다.";
            //console.dir(res.headers["jwt-auth-token"]);
            this.setInfo(
              "성공",
              res.headers["jwt-auth-token"],
              JSON.stringify(res.data.data)
            );
            storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
            let user = {
              id: res.data.data.id,
              password: res.data.data.password,
              email: res.data.data.email,
              name: res.data.data.name,
              address: res.data.data.address,
              regidate: res.data.data.regiDate,
              phone: res.data.data.phone,
              grade: res.data.data.grade,
            //  attention: res.data.data.attention
            };
            // 객체를 저장하기 위해 JSON stringify로 스트링화 시켜주고
            storage.setItem("login_user", JSON.stringify(user));
            // 이를 다시 꺼내 올때 parse를 통해서 객체를 Json화 시켜서 읽어 올 수 있게 한다.
            let tempUser = JSON.parse(sessionStorage.getItem("login_user"));
            //console.log(tempUser.id);
            //console.dir(tempUser);
            this.$store.dispatch("setLoginId", tempUser.id);
            console.log("login state id: " + this.$store.state.loginid);
            console.log("login state id2: " + this.$store.getters.getid);
            alert("로그인 성공");
            this.$router.push("/");
          } else {
            this.setInfo("", "", "");
            this.message = "로그인해주세요.";
            alert("입력 정보를 확인하세요.");
          }
        })
        .catch(e => {
          //console.dir(e);
          this.setInfo("실패", "", JSON.stringify(e.response || e.message));
          alert("로그인 실패 입력 정보를 확인하세요.");
        });
    },
    init() {
      if (storage.getItem("jwt-auth-token")) {
        this.message = storage.getItem("login_user") + "로 로그인 되었습니다.";
      } else {
        storage.setItem("jwt-auth-token", "");
      }
    },
    mounted() {
      this.init();
    }
  }
};
</script>

<style></style>
