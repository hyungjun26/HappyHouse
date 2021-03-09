<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > QnA</h2>
        </div>
      </div>
    </section>
    <div class="row">
      <div class="col-md-4"></div>
      <div v-if="items.length" class="col-md-4">
        <table class="table table-bordered table-condensed">
          <colgroup>
            <col :style="{ width: '10%' }" />
            <col :style="{ width: '45%' }" />
            <col :style="{ width: '15%' }" />
            <col :style="{ width: '20%' }" />
          </colgroup>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
          </tr>
          <tr v-for="(item, index) in items" :key="index + '_items'">
            <td>{{ item.qnaNo }}</td>
            <td>
              <router-link :to="'QnaDetail?qnaNo=' + item.qnaNo">
                {{
                item.qnaTitle
                }}
              </router-link>
            </td>
            <td>{{ item.qnaUserid }}</td>
            <td>{{ getFormatDate(item.qnaDatetime) }}</td>
          </tr>
        </table>
      </div>
      <div v-else>QNA 글이 없습니다.</div>
    </div>
    <div class="text-center">
      <button class="btn btn-primary" @click="movePage">등록</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
const storage = window.sessionStorage;
export default {
  name: "QnaList",
  data() {
    return {
      items: []
    };
  },
  created() {
    let token = storage.getItem("jwt-auth-token");
    if (token == null || token == "") {
      alert("회원이 아니면 이용할 수 없습니다.");
      this.$router.push("/");
    } else {
      axios({
        method: "get",
        url: "http://localhost:8080/api/qna"
      }).then(({ data }) => {
        console.dir(data);
        this.items = data;
        console.log(this.items);
      });
    }
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    },
    movePage() {
      this.$router.push("/QnaCreate");
    }
  }
};
</script>

<style></style>
