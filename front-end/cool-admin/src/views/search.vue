<template>
  <Row type="flex" justify="center" align="middle">
        <Col :xs="23"  :md="23" :lg="19" class="pd-bt-10">
          <Col span="10" offset="4"> 
          <form action="">
          <Input  size="large" placeholder="large size" v-model="search_data"> </Input>
          
          </form>
          </Col>
          <Col  span="10" class="search-botton-box">
          <Button type="success" v-on:click="goSearch">搜索</Button>
          </Col>
        </Col>
        <Col :xs="23"  :md="23" :lg="19" class="pd-bt-10">
            <Col span="18" offset="4" >
               <div>找到约{{queried_data.length}}条</div>
               <br>
                <ul>
                    <li class="query-list-box" v-for='item  in queried_data' :key="item.index" >
                        <h3 v-on:click="go(item.blogid)">{{item.title}}</h3>
                        <p>{{item.summary}}</p>
                    </li>
                </ul>
                <Spin size="large" v-if='loading'></Spin>
            </Col>
        </Col>
       
       
  </Row>
</template>
<script>
import service from '../utils/service.js'
export default {
  data () {
    return {
      search_data: '',
      queried_data: '',
      loading: true
    }
  },
  methods: {
    initSearch () {
      let qq = JSON.stringify(this.$route.query.q)
      this.search_data = this.$route.query.q
      let that = this
    //   console.log(qq)
      service({
        method: 'post',
        url: '/api/search',
        data: qq
      })
      .then(function (response) {
        // console.log(response)
        if (response.status === 200 && response.data.code === 200) {
          that.loading = false
          that.queried_data = response.data.blogs
        }
      })
    },
    go (id) {
      this.$router.push({name: 'blog', params: {id: id}})
    },
    goSearch () {
      let data = this.search_data
      this.$router.push({path: 'search', query: {q: data}})
      this.initSearch()
    }
  },
  mounted () {
    this.initSearch()
  }
}
</script>

<style scoped>
.search-botton-box{
    padding-left: 20px
}
.pd-bt-10{
    padding-bottom: 10px
}
.query-list-box{
    padding-bottom: 10px
}
.query-list-box p{
    overflow: hidden;
text-overflow:ellipsis;
white-space: nowrap;
}
.query-list-box h3{
    cursor: pointer;
}
 .demo-spin-col{
        height: 100px;
        position: relative;
        border: 1px solid #eee;
    }
</style>


