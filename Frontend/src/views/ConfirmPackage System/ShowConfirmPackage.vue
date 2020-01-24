<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบแสดงข้อมูลยืนยันการรับพัสดุ</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">

            <b-row class="mt-4 mb-4">
                <b-col cols="1"></b-col>
                <b-col cols="5">

                    <b-form-input class="mt-4" list="my-list-id" name="packageCode" id="packageCode" v-model="packageCode" placeholder="กรอก package code"></b-form-input>
                    <datalist id="my-list-id">
                        <option v-for="pack in allPackage" v-bind:key="pack">{{ pack.code }}</option>
                    </datalist>

                    <b-button class="mt-2" @click="this.Search">แสดงข้อมูลยืนยันการรับพัสดุ</b-button>
                </b-col>
                <b-col cols="1"></b-col>
                <b-col>
                    <div class="text-left mt-2 text-break">
                        <div v-if="this.foundPackage">
                            <hr>
                            <div class="ml-2">
                                <p class="text-center mb-1"><b>ข้อมูล Package</b></p>
                                ชื่อผู้ส่ง : {{this.packageData.createBy.name}} <br>
                                ชื่อผู้รับ : {{this.packageData.receiever}} <br>
                                ต้นสถานี : {{this.packageData.station.name}} <br>
                                ปลายทางสถานี : {{this.packageData.place}} <br>
                            </div>
                        </div>

                    </div>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>
                <b-col cols="1"></b-col>
                <b-col>
                    <b-card-body class="text-center">
            <b-card-title>ข้อมูลสมาชิก</b-card-title>

            <b-table striped hover :items="this.getConfirmPackage"></b-table>
        </b-card-body>
                </b-col>
            <b-row>
        </b-card-body>
    </b-card>
</div>
</template>

<script>
import api from "../../apiConnector"
export default {
    data() {
        return {
            foundPackage: false,
            haveSearch: false,
            packageData: {
                receiever: "None",
                place: "Arrive",
                createBy: {
                    name: "Annonymous"
                },
                station: {
                    "name": "Origin"
                }
            },
            allPackage: "",
            packageSearchData: {
                packageId: "",
                packageCode: "",
            },
            confirmPackageData: []
        }
    },
    methods: {
        Search() {
            this.findPackageById()
            this.haveSearch = true
        },
        findPackageById() {
            api.get("/findPackageByCode/" + this.packageCode)
                .then(
                    response => {
                        this.packageData = response.data
                        this.foundPackage = true
                    }
                )
        },
        getConfirmPackage() {
            api.get("/getConfirmPackage")
                .then(response => {
                    var object = this.sourceIndex(response.data)
                    var dict = []
                    for (var i in object) {
                        var item = {}
                        item["id"] = object[i].id
                        item["Code	"] = object[i].Code
                        item["Name	"] = object[i].Name
                        item["SatisfactionLevel	"] = object[i].satisfactionLevel_name
                        item["CreateBy	"] = object[i].createBy.name
                        dict[i] = item
                    }

                    this.ConfirmPackage = dict
                })
        },
        getAllPackage() {
            api.get("/getAllPackage")
                .then(response => {
                    this.allPackage = response.data
                })
        },
        sourceIndex: function (arr) {
            return arr.slice().sort(function (a, b) {
                return a.id - b.id;
            });
        },
    },
    mounted() {
        this.getAllPackage()
    }
}
</script>
