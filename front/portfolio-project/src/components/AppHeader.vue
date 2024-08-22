<template>
  <header>
    <span class="material-icons hamburger" @click="drawer = true">menu</span>
    <a href="http://localhost:8081/" class="title sixtyfour-font">Portfolio Website</a>
    <nav>
      <ul class="menu-group sixtyfour-font menuItem">
        <li class="menu-item"><a href="http://localhost:8081/#hero-head">HOME</a></li>
        <li class="menu-item"><a href="http://localhost:8081/#about-head">PROFILE</a></li>
        <li class="menu-item"><a href="http://localhost:8081/#skills-head">SKILLS</a></li>
        <li class="menu-item"><a href="http://localhost:8081/#works-head">WORKS</a></li>
      </ul>
    </nav>
    <v-navigation-drawer v-model="drawer" fixed temporary>
      <v-list nav>
        <v-list-item>
          <li class="menu-item"><a href="http://localhost:8081/#hero-head">HOME</a></li>
        </v-list-item>
        <v-list-item>
          <li class="menu-item"><a href="http://localhost:8081/#about-head">PROFILE</a></li>
        </v-list-item>
        <v-list-item>
          <li class="menu-item"><a href="http://localhost:8081/#skills-head">SKILLS</a></li>
        </v-list-item>
        <v-list-item>
          <li class="menu-item"><a href="http://localhost:8081/#works-head">WORKS</a></li>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </header>
</template>

<script>
import constants from '../common/constants'

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
header {
  display: flex;
  width: 100%;
  height: 8%;
  align-items: center;
  position: fixed;
  background-color: white;
  z-index: 2;
  box-shadow: 0px 6px 3px -3px rgba(0, 0, 0, 0.2);
}

a {
  color: black;
}

.title {
  margin-top: 5px;
  margin-right: auto;
  margin-left: 5%;
  font-size: 14px;
  cursor: pointer;
  text-decoration:none;

  @include display_pc {
    margin-right: auto;
    margin-left: 2%;
    font-size: 14px;
    cursor: pointer;
    font-size: 24px;
  }
}

.menu-item {
  list-style: none;
  display: inline-block;
  padding: 10px;
}

.hamburger {
  margin-left: 16px;

  @include display_pc {
    display: none;
  }
}

.menuItem {
  display: none;
  @include display_pc {
    display: flex;
  }
}
</style>