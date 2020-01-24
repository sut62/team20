<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบแสดงค่าใช้จ่ายการรับฝากพัสดุ</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">
            <b-card-title>ใส่ Code ของพัสดุเพื่อค้นหา</b-card-title>

            <hr>

            <b-row class="mt-4 mb-4">
                <b-col cols="2"></b-col>
                <b-col cols="8">
                    <label for="input-with-list">Code Package</label>

                    <b-row>
                        <b-col cols="8">
                            <b-form-input list="input-list" v-model="codePackage" id="input-with-list"></b-form-input>
                        </b-col>
                        <b-col cols="4">
                            <b-button style="width:100%" @click="this.sendToFind">ค้นหา</b-button>
                        </b-col>
                    </b-row>
                </b-col>

                <b-col cols="2"></b-col>
            </b-row>
        </b-card-body>
    </b-card>
</div>
</template>

<script>

export default {
    data() {
        return {
            codePackage: ""
        }
    },
    methods: {
        sendToFind(){
            localStorage.setItem("PackageCode",JSON.stringify(this.codePackage))                 
            this.$router.push("displaypackagepayment")
        }
    },

}
</script>
