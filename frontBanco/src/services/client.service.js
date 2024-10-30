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


const postClientIncome = (id, data) => {
    return axios.post(`/api/v1/clients/${id}/uploadIncome`, data);
};

const postCreditHistory = (id, data) => {
    return axios.post(`/api/v1/clients/${id}/uploadCreditHistory`, data);
};

const postAppraisalCertificate = (id, data) => {
    return axios.post(`/api/v1/clients/${id}/uploadAppraisalCertificate`, data);
};

const postDeedFristProperty = (id, data) => {
    return axios.post(`/api/v1/clients/${id}/uploadDeedFristProperty`, data);
};

const postBuisnessPlan = (id, data) => {
    return axios.post(`/api/v1/clients/${id}/uploadBuisnessPlan`, data);
};

const postFinancialStatements = (id, data) => {
    return axios.post(`/api/v1/clients/${id}/uploadFinancialStatements`, data);
};

const postRemodelingBudget = (id, data) => {
    return axios.post(`/api/v1/clients/${id}/uploadRemodelingBudget`, data);
};

const postUpdatedAppraisalCertificate = (id, data) => {
    return axios.post(`/api/v1/clients/${id}/uploadUpdatedAppraisalCertificate`, data);
};


const getClientIncome = (id) => {
    return axios.get(`/api/v1/clients/${id}/downloadIncome`);
};


export default {
    getAllClients,
    getClient,
    saveClient,
    updateClient,
    deleteClient,
    postClientIncome,
    postCreditHistory,
    postAppraisalCertificate,
    postDeedFristProperty,
    postBuisnessPlan,
    postFinancialStatements,
    postRemodelingBudget,
    postUpdatedAppraisalCertificate,
    getClientIncome
};
