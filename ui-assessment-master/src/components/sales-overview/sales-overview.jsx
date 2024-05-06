import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import React from "react";
import {faInfoCircle, faUpload} from "@fortawesome/free-solid-svg-icons";
import styled from "styled-components";
import PropTypes from 'prop-types';

const SalesOverviewContainer = styled.div`
    display: flex;
    flex-direction: column;
    padding: 10px;
    border-radius: 5px;
    background-color: white;
    width: 60%;
    margin: auto;
    margin-top: 30px;

    @media (max-width: 768px) {
        width: 100%;
    }
`;

const Header = styled.div`
    display: flex;
    justify-content: space-between;
    margin: 5px;
    padding: 5px;
`;

const SalesText = styled.span`
    display: flex;
    align-items: center;
    flex: 1;
    font-size: 1.2em;
    font-weight: bold;
`;

const InfoIcon = styled.span`
    display: flex;
    align-items: center;
`;

const PercentagesContainer = styled.div`
    display: flex;
    justify-content: space-evenly;
    border-radius: 5px;
    border-top: 1px solid #d0d0d0;
    margin-top: 10px;
    margin-bottom: 10px;
`;

export const SalesOverview = ({data}) => {
    const successfulUploads = (data.successfulUploads / data.uploads) * 100;
    const linesSaved = (data.linesSaved / data.linesAttempted) * 100;

    return (
        <SalesOverviewContainer>
            <Header>
                <SalesText>
                    <FontAwesomeIcon icon={faUpload} style={{marginRight: "0.30em"}}/>
                    <span>Sales</span>
                </SalesText>
                <InfoIcon>
                    <FontAwesomeIcon icon={faInfoCircle} aria-label="Information"/>
                </InfoIcon>
            </Header>
            <div style={{textAlign: "start", margin: "10px"}}>
                <span>You had <span style={{fontWeight: "bold"}}>{data.uploads} uploads </span>
                    and <span style={{fontWeight: "bold"}}>{data.linesSaved}</span> lines added.
                </span>
            </div>
            <PercentagesContainer>
                <span style={{marginRight: "70px"}}>
                  <p style={{color: "green", fontSize: "35px", marginBottom:"0px"}}>
                    {successfulUploads.toFixed(2)}%
                  </p>
                    <span style={{color: "gray"}}>UPLOAD SUCCESS</span>
                </span>
                <span style={{borderRight: "1px solid #d0d0d0"}}></span>
                <span style={{marginLeft: "70px"}}>
                  <p style={{color: "green", fontSize: "35px", marginBottom:"0px"}}>{linesSaved.toFixed(2)}%</p>
                  <span style={{color: "gray"}}>LINES SAVED</span>
                </span>
            </PercentagesContainer>
        </SalesOverviewContainer>
    );
};

SalesOverview.propTypes = {
    data: PropTypes.shape({
        successfulUploads: PropTypes.number.isRequired,
        uploads: PropTypes.number.isRequired,
        linesSaved: PropTypes.number.isRequired,
        linesAttempted: PropTypes.number.isRequired,
    }).isRequired,
};