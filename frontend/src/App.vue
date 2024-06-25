<template>
  <v-app>
    <header>
      <v-app-bar
        app
        dark
      >
        <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
        <v-toolbar-title>MyPortfolioSite</v-toolbar-title>
        <v-tabs>
          <v-tab
            v-for="(menuItem, index) in menuItems"
            :key="index"
          >
            {{ menuItem.name }}
          </v-tab>
        </v-tabs>
      </v-app-bar>
        <v-navigation-drawer
            v-model="drawer"
            fixed
            temporary
        >
        <v-list
            nav
            dense
        >
          <v-list-item-group>
              <v-list-item>
                <v-list-item-title>HOME</v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>ABOUT</v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>WORK</v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>SERVICE</v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>BLOG</v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>ACCESS</v-list-item-title>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-navigation-drawer>
    </header>
  </v-app>
</template>

<script>
import constants from './common/constants'

export default {
  data () {
    return {
      drawer: false,
      menuItems: constants.menuItems
    }
  }
}
</script>

<style lang="scss" scoped>
.v-toolbar__title {
  overflow: visible !important;
  margin-right: 50px !important;
}
.v-app-bar__nav-icon {
  @include display_pc {
    display: none !important;
  }
}
</style>