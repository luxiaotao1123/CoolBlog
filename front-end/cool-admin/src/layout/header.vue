<template>
   
        <Menu mode="horizontal" theme="dark" active-name="1">
              <div class="layout-logo"></div>
              <Button @click.native="submit"type="primary" size="large" class="logout">logout</Button>
        </Menu>
   
</template>
<script>
  import { mapActions } from 'vuex'
  export default {
    methods: {
      ...mapActions(['UserLogout']),
      submit () {
        this.UserLogout()
        this.$router.push({
          name: 'Login'
        })
      }
    }
  }
</script>

<style scoped>
.layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
    }
    .layout-logo{
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }
    .app-header {
    height: 60px;
    line-height: 60px;
    background: #fff;
    color: #c0ccda;
    display: flex;
    position: fixed;
    width:100%;
    z-index:3;
    box-shadow: 0px 0px 5px #f9f9f9;}
    .fixed {
      position: absolute;
      top: 0px;
      bottom: 0px;
      width: 100%;
    }
    .logout{
      float: right;
    }
</style>
