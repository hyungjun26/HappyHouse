import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../components/Index.vue";
import Login from "../components/user/Login.vue";
import Join from "../components/user/Join.vue";
import JoinSuccess from "../components/user/JoinSuccess.vue";
import Mypage from "../components/user/Mypage.vue";
import Withdraw from "../components/user/Withdraw.vue";
import FindPwd from "../components/user/FindPwd.vue";

import QnaList from "../components/QNA/QnaList.vue";
import QnaCreate from "../components/QNA/QnaCreate.vue";
import QnaDetail from "../components/QNA/QnaDetail.vue";
import QnaDelete from "../components/QNA/QnaDelete.vue";
import QnaUpdate from "../components/QNA/QnaUpdate.vue";
import QnaReply from "../components/QNA/QnaReply.vue";

import NoticeList from "../components/notice/NoticeList.vue";
import NoticeCreate from "../components/notice/NoticeCreate.vue";
import NoticeDetail from "../components/notice/NoticeDetail.vue";
import NoticeDelete from "../components/notice/NoticeDelete.vue";
import NoticeUpdate from "../components/notice/NoticeUpdate.vue";

import PreferArea from "../components/PreferArea/PreferArea.vue";

import DealList from "@/components/Deal/DealList.vue";
import DealDetail from "@/components/Deal/DealDetail.vue";

Vue.use(VueRouter);

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "Index",
      component: Index,
    },
    {
      path: "/login",
      name: "Login",
      component: Login,
    },
    {
      path: "/join",
      name: "Join",
      component: Join,
    },
    {
      path: "/joinsuccess",
      name: "JoinSuceess",
      component: JoinSuccess,
    },
    {
      path: "/mypage",
      name: "Mypage",
      component: Mypage,
    },
    {
      path: "/withdraw",
      name: "Withdraw",
      component: Withdraw,
    },
    {
      path: "/findpwd",
      name: "FindPwd",
      component: FindPwd,
    },
    {
      path: "/QnaList",
      name: "QnaList",
      component: QnaList,
    },
    {
      path: "/QnaCreate",
      name: "QnaCreate",
      component: QnaCreate,
    },
    {
      path: "/QnaDetail",
      name: "QnaDetail",
      component: QnaDetail,
    },
    {
      path: "/Qnaupdate",
      name: "Qnaupdate",
      component: QnaUpdate,
    },
    {
      path: "/QnaDelete",
      name: "QnaDelete",
      component: QnaDelete,
    },
    {
      path: "/QnaReply",
      name: "QnaReply",
      component: QnaReply,
    },
    {
      path: "/NoticeList",
      name: "NoticeList",
      component: NoticeList,
    },
    {
      path: "/NoticeCreate",
      name: "NoticeCreate",
      component: NoticeCreate,
    },
    {
      path: "/NoticeDetail",
      name: "NoticeDetail",
      component: NoticeDetail,
    },
    {
      path: "/NoticeUpdate",
      name: "NoticeUpdate",
      component: NoticeUpdate,
    },
    {
      path: "/NoticeDelete",
      name: "NoticeDelete",
      component: NoticeDelete,
    },
    {
      path: "/PreferArea",
      name: "PreferArea",
      component: PreferArea,
    },
    {
      path: "/deallist",
      name: "deallist",
      component: DealList,
    },
    {
      path: "/dealdetail",
      name: "dealdetail",
      component: DealDetail,
    },
  ],
});
