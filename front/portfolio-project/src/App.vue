<template>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Bad+Script&display=swap" rel="stylesheet">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Sixtyfour&display=swap" rel="stylesheet">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Workbench&display=swap" rel="stylesheet">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Neonderthaw&display=swap" rel="stylesheet">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@300&display=swap" rel="stylesheet">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Reggae+One&display=swap" rel="stylesheet">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Mochiy+Pop+One&display=swap" rel="stylesheet">
  <v-app>
    <AppHeader/>
    <v-content>
      <router-view/>
    </v-content>
    <AppFooter/>
  </v-app>
</template>

<script>
import AppHeader from './components/AppHeader.vue'
import AppFooter from './components/AppFooter.vue'
import $ from "jquery";

export default {
  name: 'App',
  components: {
    AppHeader,
    AppFooter
  }
}

$(window).scroll(function (){
	$('.fadein').each(function(){
		var elemPos = $(this).offset().top,
		scroll = $(window).scrollTop(),
		windowHeight = $(window).height();

			if (scroll > elemPos - windowHeight + 150){
				$(this).addClass('scrollin');
			}
	});
});
</script>

<style>
html {
  scroll-behavior: smooth;
}

.bad-script-regular {
  font-family: "Bad Script", cursive;
  font-weight: 400;
  font-style: normal;
}
.sixtyfour-font {
  font-family: "Sixtyfour", sans-serif;
  font-optical-sizing: auto;
  font-weight: 400;
  font-style: normal;
  font-variation-settings:
    "BLED" 0,
    "SCAN" 0;
}
.workbench-font {
  font-family: "Workbench", sans-serif;
  font-optical-sizing: auto;
  font-weight: 400;
  font-style: normal;
  font-variation-settings:
    "BLED" 0,
    "SCAN" 0;
}
.neonderthaw-regular {
  font-family: "Neonderthaw", cursive;
  font-weight: 400;
  font-style: normal;
}
.m-plus-rounded-1c-light {
  font-family: "M PLUS Rounded 1c", sans-serif;
  font-weight: 300;
  font-style: normal;
}
.reggae-one-regular {
  font-family: "Reggae One", system-ui;
  font-weight: 400;
  font-style: normal;
}
.mochiy-pop-one-regular {
  font-family: "Mochiy Pop One", sans-serif;
  font-weight: 400;
  font-style: normal;
}

.scroll-space{
  box-sizing: border-box;
  padding-top: 300px;
  height: 100px;
  color: #fff;
  overflow: hidden;
  .blue{
    background: #77BBCF;
    width: 200px;
    height: 200px;
    padding: 30px;
    margin: 0 auto 50px;
  }
  .pink{
    background: #FFB4B4;
    width: 200px;
    height: 200px;
    padding: 30px;
    margin: 0 auto 50px;
  }
  .yellow{
    background: #F8913C;
    width: 200px;
    height: 200px;
    padding: 30px;
    margin: 0 auto 50px;
  }
  .green{
    background: #26AA5A;
    width: 200px;
    height: 200px;
    padding: 30px;
    margin: 0 auto 50px;
  }
}
.fadein {
    opacity: 0;
    transform: translate(0,0);
    transition: all 1.5s;
  &.fadein-left{
      transform: translate(-30px,0);
  }
  &.fadein-right{
      transform: translate(30px,0);
  }
  &.fadein-up{
      transform: translate(0,-30px);
  }
  &.fadein-down{
      transform: translate(0,30px);
  }
  &.scrollin{
    opacity: 1 !important;
    transform: translate(0, 0) !important;
  }
}


.sentence {
  display: flex;
  text-align: center;
  width: 100%;
}
.sentence-0 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1s forwards;
}
.sentence-1 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1.1s forwards;
}
.sentence-2 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1.2s forwards;
}
.sentence-3 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1.3s forwards;
}
.sentence-4 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1.4s forwards;
}
.sentence-5 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1.5s forwards;
}
.sentence-6 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1.6s forwards;
}
.sentence-7 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1.7s forwards;
}
.sentence-8 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1.8s forwards;
}
.sentence-9 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 1.9s forwards;
}
.sentence-10 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2s forwards;
}
.sentence-11 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2.1s forwards;
}
.sentence-12 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2.2s forwards;
}
.sentence-13 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2.3s forwards;
}
.sentence-14 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2.4s forwards;
}
.sentence-15 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2.5s forwards;
}
.sentence-16 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2.6s forwards;
}
.sentence-17 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2.7s forwards;
}
.sentence-18 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2.8s forwards;
}
.sentence-19 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 2.9s forwards;
}
.sentence-20 {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromBottom 3s forwards;
}
@keyframes fadeInFromBottom {
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.fadeInAnimation {
  position: relative;
  opacity: 0;
  transform: translateY(100%);
  animation: fadeInFromTop 1s forwards;
}

@keyframes fadeInFromTop {
  from {
    opacity: 0;
    transform: translateY(100%);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.fadeOutAnimation {
  position: relative;
  opacity: 1;
  transform: translateY(0);
  animation: fadeOutFromBottom 1s forwards;
}
@keyframes fadeOutFromBottom {
  to {
    opacity: 0;
    transform: translateY(100%);
  }
}

.slide-in {
  font-size: 30px;
  font-weight: bold;
  animation: slideIn 1s ease-out;
}
@keyframes slideIn {
  0% {
    transform: translateX(-100%);
    opacity: 0;
  }
  100% {
    transform: translateX(0);
    opacity: 1;
  }
}

.head-margin {
  height: 8%;
}

@keyframes fadeIn {
  0% {
    display: none;
    opacity: 0;
  }

  1% {
    display: block;
    opacity: 0;
  }

  100% {
    display: block;
    opacity: 1;
  }
}

.element {
  display: none;
  opacity: 0;
}
.element.-visible {
  display: block;
  opacity: 1;
  animation: fadeIn 0.3s ease-in 0s forwards;
}

@keyframes fadeOut {
  100% {
    display: none;
    opacity: 0;
  }

  1% {
    display: block;
    opacity: 1;
  }

  0% {
    display: block;
    opacity: 1;
  }
}

.element {
  display: none;
  opacity: 0;
}
.element.-hidden {
  display: block;
  opacity: 1;
  animation: fadeOut 0.3s ease-out 0s forwards;
}
</style>