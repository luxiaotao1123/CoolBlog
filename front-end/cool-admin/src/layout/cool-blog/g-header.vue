<template>
  <div class="global-nav">
      <h2 class="cool-logo"><img src="static/images/cool-log.jpg" width="170px"></h2>
      <div class="nav-control" @click="fadeIn"><Icon type="navicon-round"></Icon></div>
      <nav class="cool-nav-content" id='cool-nav' >
          <div class="close_icon"@click="fadeOut" ><Icon type="close-round"></Icon></div>
          <ul class="nav-links" id='nav-l' >
              <li @click="fadeOut">
                <router-link to="/home"><Icon type="android-home"></Icon>  首页  </router-link>
                </li>
              <li @click="fadeOut">
                  <router-link to="/category"><Icon type="ios-pricetags"></Icon>  分类  </router-link>
              </li>
              <li @click="fadeOut">
                  <router-link to="/archives"><Icon type="android-archive"></Icon>  归档  </router-link>
              </li>
              <li @click="fadeOut">
                  <router-link to="/login"> <Icon type="ios-infinite"></Icon>  关于  </router-link>
              </li>
              <li>
                  <Input  placeholder="Enter something..." style="width: 200px" class="search-box" v-model="search_data"  v-on:click="search()">
                  <Button slot="append" icon="android-search" v-on:click="search"></Button>
                  </Input>
              </li>
          </ul>
         
      </nav>

      
  
  </div>
</template>
<script>
export default {
  data () {
    return {
      show: true,
      search_data: ''
    }
  },
  methods: {
    fadeIn () {
    //   let nav = document.getElementById('nav-l')
      let navs = document.getElementById('cool-nav')
      navs.classList.add('fade-enter')
      console.log(this.$route)
    },
    fadeOut () {
      let navs = document.getElementById('cool-nav')
      navs.classList.remove('fade-enter')
    },
    search () {
      let data = this.search_data
      if (data !== null && data.length !== 0) {
        this.$router.push({path: 'search', query: {q: data}})
        this.fadeOut()
      }
      console.log(data)
    }
  }
}
</script>
<style scoped>
.global-nav{
    border-bottom: 1px solid #eef2f8;
        position: fixed;
    z-index: 1000;
    top: 0px;
    background: #fff;
    width: 100%;
}
.search-box .ivu-icon{
   cursor: pointer;
}
.nav-control{
    box-sizing: content-box;
    cursor: pointer;
    display: block;
    position: absolute;
    font-size: 25px;
    width: 37px;
    height: 45px;
    z-index: 2;
    -webkit-box-flex: 0;
    -ms-flex: 0 0 20px;
    flex: 0 0 20px;
    right: 10px;
    top: 15px;
}
/* .nav-fade-enter-active{
  transition: all .5s ease;
} */
.fade-enter{
/* -webkit-transition: all 3s cubic-bezier(1.0, 0.5, 0.8, 1.0); */
transition: all 1s ease;
 -webkit-transition: all 1s cubic-bezier(1.0, 0.5, 0.8, 1.0);
 opacity: 1 !important;
  transform: translateX(0%)!important ;
  z-index: 3 !important;
  width: 250px !important;
/* display: block !important; */
}
.nav-fade, .nav-fade-leave-to {
  opacity: 0.5;
  right: 0px;
}
.nav-links{   
    margin-top: 50px
}
.nav-links li{
padding: 0 20px;
position: relative;
}
.nav-links>li>a{
    font-size: 18px;
    display: block;
    padding-left: 48px;
    height: 44px;
    line-height: 44px;
    font-weight: 500;
    color: #727272;
    text-decoration: none;
}
.nav-links>li>a>i{
    font-size: 20px;
    padding-right: 20px
}
.cool-nav-content{
    right: 0px;
    transform: translateX(80px);
    position: fixed;
    width:0px;
    background: #eef1f5;
   box-shadow: 0 0 30px rgba(0, 0, 0, 0.3);
    overflow-y: auto;
    z-index: 1;
    opacity: 0;
}
.cool-nav-content .close_icon{
    font-size: 25px;
    padding-left: 20px;
    cursor: pointer;
    display: block;
    padding-top: 10px;
}

@media (min-width:1024px){
.cool-logo{
    float: left;
    margin-right: 2em;
    margin-left: 1em;
    overflow: hidden;
    position: relative;
    width: 180px;
    height: 50px;
}
.cool-nav-content{
    top: auto;
    right: auto;
    width: 720px !important;
    height: auto;
    position: relative;
    background-color: transparent;
    /* display: -webkit-box;
    display: -ms-flexbox;
    display: flex; */
    /* -webkit-box-orient: horizontal;
    -webkit-box-direction: normal;
    -ms-flex-direction: row; */
    /* flex-direction: row; */
    padding-top: 0;
    opacity: 1;
    left: 170px !important;
     border-left: none;
     box-shadow: none;
    transition: all 0.4s cubic-bezier(0.4, 0, 0, 1);
}
.cool-nav-content .close_icon{
display: none
}
.cool-nav-content .nav-links>li>a>i{
    font-size: 20px;
    display: none 
}
.global-nav .nav-links{
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    border-top: none;
    display: -webkit-box;
    display: -ms-flexbox;
    display: -webkit-box;
    -webkit-box-flex: 1;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    margin: 0;
    padding: 0;
    border-bottom: none;
    white-space: nowrap;
}
.global-nav .nav-links>li{
font-size: 14px;
padding: 17px 1.2rem;
}
.global-nav .nav-links>li>a{
        /* color: #fff!important; */
            padding: 0 13px;
    display: inline-block;
    /* line-height: 53px; */
    padding: 2px 2px;
}
.nav-control{
    display: none
}
}

.cool-logo image{
    width: 170px
}
.cool-nav-content{
   float: left;
    /* position: fixed; */
    transition: all .33s cubic-bezier(1,0,0,1);
    background-color: #fff;
    top: 0;
    /* width: 75%; */
    height: 100%;
}
.global-nav .nav-links>li>a {
color: #71777c;
}
.global-nav .nav-links>li>a:hover{
        background-color: #F3F3F3;
}

</style>

