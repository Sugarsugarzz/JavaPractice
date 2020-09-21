<template>
  <!--所有元素，不能直接写在根节点下-->
  <div>
    <h1>个人信息</h1>
    {{id}}
  </div>
</template>

<script>
export default {
  props: ['id'],
  name: "UserProfile",
  // 过滤器 chain
  beforeRouteEnter: (to, from, next) => {
    console.log("进入路由之前");  // 加载数据
    next(vm => {
      vm.getData();  // 在进入路由之前，执行 getData。
    });
  },
  beforeRouteLeave: (to, from, next) => {
    console.log("进入路由之后");
    next();
  },
  methods: {
    getData: function () {
      this.axios({
        method: 'get',
        url: 'http://localhost:8080/static/mock/data.json'
      }).then(function (response) {
        console.log(response);
      })
    }
  }
}
</script>

<style scoped>

</style>
