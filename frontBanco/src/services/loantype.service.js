import axios from "../http-common";

const requestLoanType = (rutClient,type,years,interestRate,propertyValue) => {
    return axios.get("/api/v1/loanType/request", {
        params: {
            rutClient,
            type,
            years,
            interestRate,
            propertyValue,
        },
    });
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

export default{
    requestLoanType,
    getLoanByRut,
    deleteLoanTypeByRut,
};