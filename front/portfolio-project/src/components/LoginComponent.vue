<template>
  <section class="home-hero footer-keep">
    <v-container fluid fill-height class="home-Service__content footer-keep">
      <div class="login-frame-outer">
        <div class="login-frame">
          <div class="login-input">
            <label for="username" class="input-label m-plus-rounded-1c-light">ユーザー名:</label>
            <input v-model="username" type="text" id="username" class="keyword-box" required>
          </div>
          <div class="login-input">
            <label for="password" class="input-label m-plus-rounded-1c-light">パスワード:</label>
            <input v-model="password" type="password" id="password" class="keyword-box" required>
          </div>
          <div>
            <a class="new-buttons btn-border-login m-plus-rounded-1c-light" @click="login">ログイン</a>
          </div>
          <div v-if="errorMessage" style="color: red;">{{ errorMessage }}</div>
        </div>
      </div>
    </v-container>
  </section>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    async login() {
      try {
        const response = await fetch("http://localhost:8080/login", {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          body: `username=${encodeURIComponent(this.username)}&password=${encodeURIComponent(this.password)}`
        });
        if (response.ok) {
          window.location.href = "/service";
        } else {
          this.errorMessage = "ログインに失敗しました。";
        }
      } catch (error) {
        console.error("ログインエラー:", error);
        this.errorMessage = "サーバーエラーが発生しました。";
      }
    }
  }
}
</script>

<style scoped>
.home-Service__content {
  background: url("../assets/img/home-service.jpg");
  background-size: cover;
  background-attachment: fixed;
  background-position: center center;
  height: 100%;
}
.login-frame-outer {
  justify-content: center;
  margin-top: 90px;
  display: flex;
}
.login-frame {
  background-color: lightgray;
  padding: 16px;
  border-radius: 8px;
  margin-bottom: 16px;
}
.search-keyword-frame {
  display: flex;
  justify-content: center;
  gap: 16px;
}
.search-tag-frame {
  display: flex;
  gap: 16px;
  margin-top: 16px;
}
.keyword-box {
  background-color: white;
  padding: 16px;
  border-radius: 4px;
  border: 1px solid #aaa;
}
.or-checkbox {
  display: flex;
  justify-content: center;
  align-items: center;
  label {
    margin-left: 4px;
  }
}
.search-range-select {
  background-color: white;
  border-radius: 4px;
  border: thick double;
  padding: 4px;
}
.search-button {
  background-color: blue;
  border-radius: 8px;
  margin-left: 8px;
  font-weight: bold;
  color: white;
  padding: 16px;
}
.new-index-create-frame {
  margin: 32px;
  justify-content: center;
  display: flex;
}
.new-index-create-button {
  background-color: blue;
  border-radius: 8px;
  font-weight: bold;
  color: white;
  padding: 8px 16px;
  justify-content: center;
}

a {
  text-decoration:none;
  font-weight: bold;
  color: white;
  padding: 0 16px 0 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
a.btn-border {
  border: 2px solid blue;
  border-radius: 16px;
  background: blue;
}
a.btn-border:hover {
  border: 2px solid blue;
  color: black;
  background: white;
}
a.btn-border-login {
  border: 2px solid black;
  border-radius: 4px;
  background: black;
  padding: 8px 16px 8px 16px;
}
a.btn-border-login:hover {
  border: 2px solid black;
  color: black;
  background: white;
  padding: 8px 16px;
}
.new-buttons {
  color: white;
  margin-top: 16px;
}
.under-margin {
  height: 10vh;
}
.login-input {
  padding: 8px;
}
.input-label {
  margin-right: 16px;
  font-weight: bold;
}
</style>