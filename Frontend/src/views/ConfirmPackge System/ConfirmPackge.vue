<template>
    <div>
        <b-card no-body>
            <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบยืนยันการรับพัสดุ</b-nav-item>
            </b-nav>
            </b-card-header>

            <b-card-body class="text-center">
            <b-card-title>แบบฟอร์มยืนยันการรับพัสดุ</b-card-title>

            <hr>

            <b-row class="mt-4 mb-4">
                <b-col cols="1"></b-col>
                <b-col cols="5">
                    
                    <label for="selectList">เลือกชื่อพนักงาน</label>
                    <b-form-select
                    v-model="this.ConfirmPackge.employeeId"
                    :options="this.confirmData"
                    class="mb-3"
                    value-field="id"
                    text-field="name"
                    disabled-field="notEnabled"
                    id="selectList"
                    ></b-form-select>

                    <label for="input-with-list">กรอก Package ID</label>
                    <b-form-input 
                        list="input-list" 
                        v-model="this.confirmData.packageId" 
                        id="input-with-list"></b-form-input>
                    <b-button class="mt-2"  @click="this.Search" >ค้นหา</b-button>
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
                               ชื่อผู้ส่ง : {{this.packageData.srcName}} <br>
                               ชื่อผู้รับ : {{this.packageData.dstName}} <br>
                               ที่อยู่ผู้รับ : {{this.packageData.srcStation}} <br>
                            </div>
                        </div>
                        
                    </div>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

            <hr v-if="this.foundPackage">

            <b-row class="mt-4 mb-4" v-if="this.foundPackage">
                
                
                <b-col cols="1"></b-col>
                <b-col>
                    <label for="selectList">เลือกระดับความพึงพอใจ</label>
                    <b-form-select
                    v-model="this.ConfirmPackge.satisfactionId"
                    :options="this.confirmData"
                    class="mb-3"
                    value-field="id"
                    text-field="name"
                    disabled-field="notEnabled"
                    id="selectList"
                    ></b-form-select>

                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

            

            <div v-if="this.foundPackage">
                <hr>

                <b-button variant="primary" @click="this.Save">บันทึก</b-button>

            </div>
            </b-card-body>
        </b-card>
    </div>
</template>
<script>
export default {
    data() {
        return {
            foundPackage: false,
            haveSearch: false,
            ConfirmPackge:{
                packageId: null,
                employeeId: null,
                satisfactionId: null,
            },
            packageData: {
                srcName: "test",
                dstName: "test2",
                srcStation: "sT",
                dstStation: "dT"
            },
            employeeData:[
                {
                    id: 1,
                    name: "test test"
                },
                {
                    id: 2,
                    name: "test2 test2"
                }
            ],
            confirmData:[
                {
                    id:1,
                    name: "Station test1",
                },
                {
                    id:2,
                    name: "Station test2",
                },
            ]
        }
    },
    methods:{
        Search(){
            this.foundPackage = true
            this.haveSearch = true
        },
        Save(){
            alert("บันทึกยืนยันการรับพัสดุสำเร็จ")
        }
    }
}
</script>