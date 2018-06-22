import React from 'react';

class OverlayMessage extends React.Component {
    render() {
        return (
            <div className={'overlay-message ' + this.props.colorClass}>
                <p className="overlay-text">
                    {this.props.overlayText}
                    <span className="icon-cross pull-right cursor-pointer"
                        onClick={this.props.handleOverlayClose.bind(this)}></span>
                </p>
            </div>
        );
    }
}

OverlayMessage.defaultProps = {
    handleOverlayClose: function() {},
    overlayText: '',
    colorClass: 'success'
};
export default OverlayMessage;
