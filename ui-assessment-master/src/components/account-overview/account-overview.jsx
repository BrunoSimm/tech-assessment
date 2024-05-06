import React from "react";
import styled from "styled-components";
import {SupportContact} from "../support-contact/support-contact";
import {SalesOverview} from "../sales-overview/sales-overview";
import PropTypes from "prop-types";

const AccountOverviewContainer = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
    flex-wrap: wrap;
    background-color: #f0f0f0;
    width: 70%;
    margin: auto;

    @media (max-width: 768px) {
        flex-direction: column;
        width: 100%;
    }
`;

export const AccountOverview = ({data}) => {
    return (
        <>
            <AccountOverviewContainer>
                <h2 style={{fontWeight: "normal", marginRight: "25px"}}>Account Overview</h2>
                <SupportContact data={data.supportContact}/>
            </AccountOverviewContainer>
            <SalesOverview data={data.salesOverview}/>
        </>
    );
};

export default AccountOverview;

AccountOverview.propTypes = {
    data: PropTypes.shape({
        supportContact: PropTypes.shape({
            name: PropTypes.string.isRequired,
            email: PropTypes.string.isRequired
        }),
        salesOverview: PropTypes.shape({
            uploads: PropTypes.number.isRequired,
            successfulUploads: PropTypes.number.isRequired,
            linesAttempted: PropTypes.number.isRequired,
            linesSaved: PropTypes.number.isRequired,
            lastUploadDate: PropTypes.number.isRequired
        })
    })
}
