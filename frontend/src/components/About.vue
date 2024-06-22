<template>
  <div class="about">
    <h1>This is an about page</h1>
    <button @click="callAPI">Call API</button>
    <h2>{{ greeting }}</h2>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "AsdfAsdf",
  data() {
    return {
      greeting: ""
    };
  },
  methods: {
    callAPI() {
      axios
        .get("http://localhost:8080/asdf")
        .then(resp => {
          this.greeting = resp.data;
        })
        .catch(err => {
          this.greeting = err;
        });
    }
  }
};
</script>