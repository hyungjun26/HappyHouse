<template>
  <div align="center">
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 회원가입 완료</h2>
        </div>
      </div>
    </section>
    <h1>회원가입이 완료되었습니다!</h1>
    <a href="#" @click="moveIndex">메인으로</a>
  </div>
</template>

<script>
export default {
  methods: {
    moveIndex() {
      this.$router.push("/");
    }
  }
};
</script>

<style></style>
