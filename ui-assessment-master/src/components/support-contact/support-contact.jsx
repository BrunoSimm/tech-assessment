import styled from "styled-components";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEnvelope} from "@fortawesome/free-solid-svg-icons";
import PropTypes from "prop-types";

export const SupportContact = ({data}) => {
    const SupportContactContainer = styled.div`
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        padding: 10px;
        border-radius: 5px;
        margin-top: 10px;
    `;
    const YellowBox = styled.div`
        background-color: yellow;
        width: 50px;
        height: 50px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 5px;
        margin-right: 10px;
    `;

    const InfosContainer = styled.div`
        display: flex;
        flex-direction: row;
        align-items: center;
        background-color: #f0f0f0;
    `;

    const ContactContainer = styled.div`
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        background-color: #f0f0f0;
        padding: 5px;
    `;

    return (
        <SupportContactContainer>
            <p style={{color: "gray"}}>YOUR FEEFO SUPPORT CONTACT</p>
            <InfosContainer>
                <YellowBox>{data.name.charAt(0).toUpperCase()}</YellowBox>
                <ContactContainer>
                    <span style={{fontWeight: "bold"}}>{data.name}</span>
                    <span>
                        <FontAwesomeIcon icon={faEnvelope} style={{marginRight: "0.5em"}}/>
                        <span style={{marginRight: "1em"}}>{data.email}</span> 020 3362 4208
                    </span>
                </ContactContainer>
            </InfosContainer>
        </SupportContactContainer>
    );
};

SupportContact.propTypes = {
    data: PropTypes.shape({
        name: PropTypes.string.isRequired,
        email: PropTypes.string.isRequired,
    }).isRequired,
};