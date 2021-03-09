<template>
  <div>
    <br />
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="d-flex justify-content-between align-items-center">
        <h2>Happy House > 뉴스 > 상세보기</h2>
      </div>
    </section>
    <br />
    <br />
    <br />
    <table class="table table-bordered text-center align-center">
      <tr>
        <th>기사 제목</th>
        <td>{{ title }}</td>
      </tr>
      <tr>
        <th>기사 원문 링크</th>
        <td>
          <a :href="originallink ">기사링크</a>
        </td>
      </tr>
      <tr>
        <th>전문</th>
        <td class="w-50 text-left">{{ description }}</td>
      </tr>
    </table>
    <div class="text-center">
      <router-link :to="'/NewsMain'">
        <button class="btn btn-primary">뉴스목록</button>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: "",
      originallink: "",
      description: ""
    };
  },
  created() {
    this.title = this.$route.query.title;
    this.originallink = this.$route.query.originallink;
    this.description = this.removetag(this.$route.query.description);
  },
  methods: {
    removetag(val) {
      let removetag = val.replace(/(<([^>]+)>)/gi, "");
      return removetag.replace(/&quot;/gi, " ");
    }
  }
};
</script>

<style>
</style>