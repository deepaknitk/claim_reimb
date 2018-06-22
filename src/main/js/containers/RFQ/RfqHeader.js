import React from 'react';

const RfqHeader = (props) => {
    return(
        <div className="margin-lr40 p-t-20 p-b-20 p-l-30 p-r-30">
            <div className = "sub_header_border_top"></div>
            <span className="subheader_span f-s-24 page-title">
                    {props.localeFile.sub_header}
                </span>
            <div className = "current_page_status"><span className= "sub_header_border_buttom">{props.localeFile.home}</span> » {props.localeFile.submission}</div>
        </div>
    );
};

export default RfqHeader;
