import React, {Component} from 'react';

class Footer extends Component {
    render(){
        return(
            <footer className="bg-dark text-light py-5">
                <div className="container py-5">
                    <div className="row">
                        <div className="col-6 col-md-6 col-lg-3 mb-2">
                            <h6 className="text-uppercase">Thanks to</h6>
                            <ul className="nav flex-column">
                                <li>Spring Boot</li>
                                <li>Gradle</li>
                                <li>TomCat</li>
                                <li>and others</li>
                            </ul>
                        </div>
                        <div className="col-12 col-md-12 col-lg-6 mb-2 text-right">
                            <h6 className="text-uppercase">Follow us for more information</h6>
                            <ul className="nav flex-column">
                                <li>Alex Mironov</li>
                                <li>Sema Putnikov</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>
        );
    }
}

export default Footer;