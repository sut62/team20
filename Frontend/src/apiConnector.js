import axios from "axios";

export default axios.create({
    baseURL: "http://192.168.1.100:9000/team20",
    headers: {
        'Access-Control-Allow-Origin': '*',
        "Content-type": "application/json",
    }
});