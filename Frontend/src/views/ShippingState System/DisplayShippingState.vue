<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบแสดงสถานะพัสดุ</b-nav-item>
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

                    <b-button class="mt-2" @click="this.Search">แสดง Timeline</b-button>
                </b-col>
                <b-col cols="1"></b-col>
                <b-col>
                    <b>สถานะการค้นหา Package</b>
                    <div class="text-left mt-2 text-break">
                        <div>
                            สถานะ :
                            <span v-if="this.haveSearch">
                                <div v-if="this.foundPackage" class="badge badge-success text-wrap" style="width: 6rem;">
                                    พบ Package
                                </div>
                                <div v-else class="badge badge-danger text-wrap" style="width: 6rem;">
                                    ไม่พบ Package
                                </div>
                            </span>

                            <span v-if="!this.haveSearch">
                                <div class="badge badge-warning text-wrap" style="width: 8rem;">
                                    รอการค้นหา package
                                </div>
                            </span>

                        </div>

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

            <hr v-if="this.foundPackage">

            <b-row class="mt-4 mb-4" v-if="this.foundPackage">

                <b-col cols="4"></b-col>
                <b-col cols="6">
                    
                    <light-timeline :items='shippingStateData'></light-timeline>

                </b-col>
                <b-col cols="2"></b-col>
            </b-row>

            <b-alert class="mt-3 mb-4" :show="saveStatus.popup.dismissCountDown" dismissible fade :variant="saveStatus.popup.variant">
                {{this.saveStatus.popup.message}}
            </b-alert>

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
            shippingStateData: null,
            saveStatus: {
                popup: {
                    dismissSecs: 3,
                    dismissCountDown: 0,
                    showDismissibleAlert: false,
                    variant: "danger",
                    message: ""
                }
            }
        }
    },
    methods: {
        Search() {
            this.findPackageById()
            this.getShippingStateByPackageCode()
            this.haveSearch = true
        },
        findPackageById() {
            api.get("/findPackageByCode/" + this.packageCode)
                .then(
                    response => {
                        this.packageData = response.data
                        this.foundPackage = true
                    },
                    error => {
                        if (error) {
                            this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                            this.saveStatus.popup.variant = "danger"
                            this.saveStatus.popup.message = "ไม่พบ package จากการค้นหากรุณาค้นหาอีกครั้ง !"
                        }
                    }
                )
        },
        getShippingStateByPackageCode() {
            api.get("/getShippingStateByPackageCode/" + this.packageCode)
                .then(
                    response => {
                        var object = this.sourceIndex(response.data)
                        var dict = []
                        //Parse shippingState to timeline
                        for(var i in object){
                            var item = {}
                            item["tag"] = object[i].onStatus
                            item["type"] = "circle"
                            item["color"] = this.getColor(object[i].onStatus)
                            item["htmlMode"] = true
                            item["content"] = '<div class="text-left"><b>ShippingState code : </b>' + object[i].code + '\n'+
                                                '<b>Timestamp : </b>' + this.getDateWithFormat(object[i].timeStamp) + '\n'+
                                                '<b>atStation : </b>' + object[i].atStation + '</div>\n'
                            dict[i] = item
                        }
                        this.shippingStateData = dict
                    }
                )
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
        getColor(status){
            if(status == "สำเร็จ")
                return "#009912"
            else if(status == "ยกเลิก")
                return "#A50000"
            else
                return "#00A4E8"
        },
        getDateWithFormat(date){
            var newDate = new Date(date)
            return newDate.getDate() + "/" + newDate.getMonth()+1 + "/" + newDate.getFullYear() + " " + newDate.getHours() +
                    ":" + newDate.getMinutes() + ":" + newDate.getSeconds()
        }
    },
    mounted() {
        this.getAllPackage()
    }
}
</script>
