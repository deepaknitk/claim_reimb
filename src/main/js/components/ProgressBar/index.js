import React from 'react';

const ProgressBar = (props) => {
    return(
        <div className="progress fileProgress center-all">
                                <div className={'progress-bar progress-bar-striped ' + props.successStatus} role="progressbar"
                                     aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"
                                     style={{
                                         width: props.fileStatus.percentage + '%'
                                     }}>{props.fileStatus.fileName}
                                </div>
    </div>
    );
};

export default ProgressBar;
