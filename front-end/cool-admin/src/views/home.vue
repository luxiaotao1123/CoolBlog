<template>
  <!-- <span>home</span> -->
<article-card :list='list.list'></article-card>
</template>
<script>
import service from '../utils/service.js'
import ArticleCard from '@/components/articlecards.vue'
export default {
  data () {
    return {
      list: {}
    }
  },
  components: {
    'ArticleCard': ArticleCard
  },
  methods: {
    initBlogs () {
      let that = this
      service.get('/api/blogs')
      .then(function (response) {
        console.log(response)
        if (response.status === 200 || response.data.code === 200) {
          that.list = response.data.blog
          console.log(that.list.list)
        }
      })
      .catch(function (error) {
        console.log(error)
      })
    }
  },
  mounted () {
    this.initBlogs()
  }
}
</script>

