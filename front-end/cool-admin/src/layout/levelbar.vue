<template>
  <section class="app-levelbar clearfix">
    <h4 class="router-name fl">{{label}}</h4>
    <Breadcrumb class="breadcrumb fr">
      <BreadcrumbItem v-for="(item, index) in list" :active="index == list.length - 1" :key="item.id">
        <router-link :to="item.path">
          {{item.meta && item.meta.label || item.name}}
        </router-link>
      </BreadcrumbItem>
    </Breadcrumb>
  </section>
</template>

<script>
export default {
  data () {
    return {
      list: []
    }
  },
  computed: {
    label () {
      return (this.$route.meta && this.$route.meta.label) || this.$route.name
    }
  },
  methods: {
    getList () {
      this.list = this.$route.matched
    }
  },
  watch: {
    $route (to, from) {
      this.getList()
    }
  },
  created () {
    this.getList()
  }
}
</script>

<style scoped>
.app-levelbar {
  background: #fff;
  border-bottom: 1px solid #dedede;
}

.breadcrumb {
  padding-right: 20px;
}
.router-name {
  padding: 8px 12px;
}
</style>

