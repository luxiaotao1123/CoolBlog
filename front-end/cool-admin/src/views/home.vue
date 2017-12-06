<template>
<div>
  <article-card :list='list.list'></article-card>
  <div style="margin-top:15px;margin-right:30px;" align="right">
    <Page :current="list.pageNum" :total="list.total" simple  @on-change ='changePage'></Page>
  </div>
</div>
</template>
<script>
import service from '../utils/service.js'
import ArticleCard from '@/components/articlecards.vue'
export default {
  data () {
    return {
      list: {},
      pageData: {}
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
    },
    changePage (val) {
      console.log(val)
      this.pageData.pageNum = val
      this.filterData()
    },
    filterData () {
      let that = this
      let pageNum = this.pageData.pageNum
      // let list = []
      service.get('/api/blogs', {
        params: {
          pageNum: pageNum
        }
      })
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

