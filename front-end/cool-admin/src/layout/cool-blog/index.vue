<template>
  <div class="layout"> 
    <app-header></app-header>
    <app-container></app-container>
    <app-footer></app-footer>
  </div>
</template>

<script>
import AppHeader from './g-header'
import AppContainer from './g-container'
import AppFooter from './g-footer'
export default {
  components: {
    AppHeader,
    AppContainer,
    AppFooter
  }
}
</script>