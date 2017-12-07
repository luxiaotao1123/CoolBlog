<template>
    <div class="post-header">
        <h1>{{blogInfo.title}}</h1>
        <div class="post-meta">
            <time>{{blogInfo.updatetime}}</time>
        </div>
        <div class="post-content" id='post-content'>
            <div class='main-content' v-html="blogInfo.content"></div>
        </div>
    </div>
</template>
<script>
import service from '../utils/service.js'
export default {
  data () {
    return {
      blogInfo: {
        title: '',
        updatetime: '',
        content: ''
      }
    }
  },
  methods: {
    initblog () {
      let id = this.$route.params.id
    //   console.log(id)
      let that = this
      service.get('/api/blog/' + id)
      .then(function (responce) {
        console.log(responce)
        if (responce.status === 200 || responce.data.code === 200) {
          that.blogInfo = responce.data.getBlog
        } else {
          that.$Message.error('获取失败! ')
        }
      })
      .catch(function (error) {
        console.log(error)
      })
    }
  },
  mounted () {
    this.initblog()
  },
  watch: {
    '$route': 'initblog'
  },
  beforeMount () {
    this.blogInfo = ''
  }
}
</script>
<style >
.post-header{
    display: block;
    max-width: 960px;
    margin: 0 auto;
    padding-right: 20px;
    padding-left: 20px
}
.post-meta{
line-height: 1.5em;
    font-family: "Open Sans",sans-serif;
    font-size: 13px;
    font-weight: 400;
    color: #9eabb3;
}
.post-content{
   text-rendering: optimizelegibility;
    letter-spacing: -.3px;
    font-family: Merriweather,serif;
    color: #5d686f;
    margin-top: 20px;
    font-weight: 400;
    overflow: hidden;
}
#main-content p img{
    max-width: 80% !important;
}
.main-content{
     display: block;
    max-width: 960px;
    margin: 0 auto;
    padding-right: 20px;
    padding-left: 20px;
    overflow: hidden;
}
.main-content img{
    max-width: 100% !important;
}
@media only screen and (min-width: 1024px){
    .post-header h1{
    font-size: 37px;
    }
    .post-content{
    font-size: 18px
    }
}
@media only screen and (max-width: 1023px) and (min-width: 768px) {
    .post-header h1{
    font-size: 30px;
    }
    .post-content{
font-size: 17px
    }
}
@media only screen and (max-width: 767px){
    h1{
        font-size: 25px;
    }
}
</style>

