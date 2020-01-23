<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบแสดงข้อมูลสมาชิก</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">
            <b-card-title>ข้อมูลสมาชิก</b-card-title>


            <b-table striped hover :items="this.MemberCustomer"></b-table>
        </b-card-body>

    </b-card>

</div>
</template>

<script>
import api from "../../apiConnector"
export default {
    data() {
        return {
            MemberCustomer: []

        }
    },
    methods: {
        getMemberCustomer() {
            api.get("/getMemberCustomer")
                .then(response => {
                    var object = this.sourceIndex(response.data)
                    var dict = []
                    for (var i in object) {
                        var item = {}
                        item["id"] = object[i].id
                        item["Member Name	"] = object[i].MemName
                        item["Member Tel	"] = object[i].Tel
                        item["Member Email	"] = object[i].email
                        item["Member Type	"] = object[i].memberType.type
                        item["Member Level	"] = object[i].memberLevel.permission
                        item["CreateBy	"] = object[i].createBy.name
                        dict[i] = item
                    }

                    this.MemberCustomer = dict
                })
        },
    sourceIndex: function (arr) {
        return arr.slice().sort(function (a, b) {
            return a.id - b.id;
        });
    }
    },

    mounted() {
        this.getMemberCustomer();
    }
}
</script>
