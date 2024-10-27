import axios from "../http-common"; 

const simulateCredit = (amount, years, interestRate, type) => {
    return axios.get("/api/v1/credit/simulate", {
        params: {
            amount,
            years,
            interestRate,
            type,
        },
    });
};

export default {
    simulateCredit,
};
