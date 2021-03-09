<template>
  <div>
    <header id="header" class="fixed-top header-inner-pages">
      <div class="container d-flex align-items-center justify-content-between">
        <h1 class="logo">
          <a href="#" @click.prevent="moveIndex">
            SSAFY
            <span>.</span>
          </a>
        </h1>
        <a href="index.html" class="logo">
          <img src="assets/img/logo.png" alt class="img-fluid" />
        </a>

        <nav class="nav-menu d-none d-lg-block">
          <ul>
            <li class="active">
              <a href="#" @click.prevent="moveIndex">Home</a>
            </li>
            <li>
              <a href="#" @click.prevent="moveNotice">공지사항</a>
            </li>
            <li>
              <a href="#services" @click.prevent="moveNews">뉴스</a>
            </li>
            <li>
              <a href="#" @click.prevent="moveQNA">QnA</a>
            </li>
            <li>
              <a href="#" @click.prevent="moveDeal">주택거래정보</a>
            </li>
            <li class="drop-down">
              <a href>관심지역</a>
              <ul>
                <li>
                  <a href="#" @click.prevent="movePrefer">관심지역설정</a>
                </li>
              </ul>
            </li>
          </ul>
        </nav>

        <template v-if="getid == ''">
          <a href="#" @click="moveLogin" class="get-started-btn login">로그인</a>
        </template>
        <template v-else>
          <p style="color: white;">
            {{ getid }}님 환영합니다
            <a href="#" @click.prevent="logout">로그아웃</a>
            <a href="#" @click.prevent="moveMypage">마이페이지</a>
          </p>
        </template>

        <!-- </c:if> -->
        <!-- <c:if test="${userinfo!=null}">
				
				<a href="${root}/user.do?act=logout">로그아웃</a>
				<a href="${root}/user/mypage.jsp">마이페이지</a>
       
        </c:if>-->
      </div>
    </header>

    <!-- End Breadcrumbs -->
  </div>
</template>

<script>
import { mapGetters } from "vuex";
const storage = window.sessionStorage;

export default {
  computed: {
    ...mapGetters(["getid"])
  },
  data() {
    return {
      id: this.$store.state.loginid
    };
  },
  methods: {
    moveNews() {
      this.$router.push("/NewsMain");
    },
    moveDeal() {
      this.$router.push("/deallist");
    },
    movePrefer() {
      this.$router.push("/PreferArea");
    },
    moveNotice() {
      this.$router.push("/NoticeList");
    },
    moveQNA() {
      this.$router.push("/QnaList");
    },
    moveIndex() {
      this.$router.push("/");
    },
    moveLogin() {
      this.$router.push("/login");
    },
    moveMypage() {
      this.$router.push("/mypage");
    },
    logout() {
      storage.setItem("jwt-auth-token", "");
      storage.setItem("login_user", "{}");
      this.message = "로그인 해주세요.";
      this.setInfo("로그아웃 성공", "", "");
      this.$store.dispatch("setLoginId", "");
      this.$router.push("/");
    },
    setInfo(status, token, info) {
      this.status = status;
      this.token = token;
      this.info = info;
    }
  },
  created() {
    let user = JSON.parse(sessionStorage.getItem("login_user"));
    console.log("created");
    //onsole.dir(user);
    // this.id = this.$store.state.loginid;
    // console.log("현재 id: " + this.id);
    // console.log("현재 id2: " + this.$store.state.loginid);
    if (
      JSON.stringify(user) == "{}" ||
      JSON.stringify(user) == "" ||
      user == null
    )
      this.$store.dispatch("setLoginId", "");
    else this.$store.dispatch("setLoginId", user.id);
    console.log("state id :" + this.$store.state.loginid);
  }
};
</script>

<style></style>
