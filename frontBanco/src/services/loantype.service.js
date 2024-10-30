import axios from "../http-common";

const getAll = () => {
    return axios.get("/api/v1/loanType/");
};

const requestLoanType = (rutClient, type, years, interestRate, propertyValue, financeRate) => {
    return axios.get("/api/v1/loanType/request", {
        params: {
            rutClient,
            type,
            years,
            interestRate,
            propertyValue,
            financeRate,  
        },
    });
};
const getLoanById = (id) => {
    return axios.get(`/api/v1/loanType/${id}`);
};
const getLoanByRut = (rutClient) => {
    return axios.get("/api/v1/loanType/client", {
        params: {
            rutClient,
        },
    });
};

const deleteLoanTypeByRut = (rutClient) => {
    return axios.delete(`/api/v1/loanType/client/${rutClient}`);
}
const deleteLoanTypeById= (id) => {
    return axios.delete(`/api/v1/loanType/${id}`);
};

const saveLoanType = (loanType) => {
    return axios.post("/api/v1/loanType/", loanType);
};
const updateLoanType = (loanType) => {
    return axios.put(`/api/v1/loanType/`, loanType);
};

export default{
    getAll,
    requestLoanType,
    getLoanById,
    getLoanByRut,
    deleteLoanTypeByRut,
    deleteLoanTypeById,
    saveLoanType,
    updateLoanType,
};