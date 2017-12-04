<template>
  <div class="global-nav">
      <h2 class="cool-logo"><img src="src/statics/images/cool.png" width="170px"></h2>
      <div class="nav-control" @click="fade"><Icon type="navicon-round"></Icon></div>
      <nav class="cool-nav-content" >
          <ul class="nav-links" id='nav-l' >
              <li>
                <router-link to="#">首页</router-link>
                </li>
              <li>
                  <router-link to="#">首页</router-link>
              </li>
              <li>
                  <router-link to="#">首页</router-link>
              </li>
              <li>
                  <router-link to="#">首页</router-link>
              </li>
          </ul>
      </nav>
  </div>
</template>
<script>
export default {
  data () {
    return {
      show: true
    }
  },
  methods: {
    fade () {
      console.log('x')
      let nav = document.getElementById('nav-l')
    //   nav.removeAttribute
      nav.classList.add('fade-enter')
    }
  }
}
</script>
<style scoped>
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
    top: 6px;
}
/* .nav-fade-enter-active{
  transition: all .5s ease;
} */
.fade-enter{
/* -webkit-transition: all 3s cubic-bezier(1.0, 0.5, 0.8, 1.0); */
transition: all 1s ease;
 -webkit-transition: all 1s cubic-bezier(1.0, 0.5, 0.8, 1.0);
 opacity: 1 !important;
/* display: block !important; */
}
.nav-fade, .nav-fade-leave-to {
  opacity: 0.5;
  right: 0px;
}
.nav-links{   
}
.cool-nav-content{
    right: 0px;
    position: absolute;
    width:250px !important
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
    width: auto;
    height: auto;
    position: relative;
    background-color: transparent;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: horizontal;
    -webkit-box-direction: normal;
    -ms-flex-direction: row;
    flex-direction: row;
    padding-top: 0;
    opacity: 1;
    left: 170px !important;
    transition: all 0.4s cubic-bezier(0.4, 0, 0, 1);
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
    display: block;
    -webkit-box-flex: 1;
    -ms-flex-positive: 1;
    flex-grow: 1;
    /* position: fixed; */
    transition: all .33s cubic-bezier(1,0,0,1);
    background-color: #fff;
    top: 0;
    width: 75%;
    height: 100%;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    /* padding-top: 55px; */
}
.global-nav .nav-links>li>a {
color: #71777c;
}
.global-nav .nav-links>li>a:hover{
        background-color: #F3F3F3;
}

</style>

