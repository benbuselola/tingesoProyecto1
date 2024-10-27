import axios from "../http-common";

const getAllClients = () => {
    return axios.get("/api/v1/clients/");
};

const getClient = (id) => {
    return axios.get(`/api/v1/clients/${id}`);
};

const saveClient = (data) => {
    return axios.post("/api/v1/clients/", data);
};

const updateClient = (data) => {
    return axios.put("/api/v1/clients/", data);
};

const deleteClient = (id) => {
    return axios.delete(`/api/v1/clients/${id}`);
};

export default {
    getAllClients,
    getClient,
    saveClient, 
    updateClient,
    deleteClient
};