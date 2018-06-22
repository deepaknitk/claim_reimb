let webpack = require('webpack');
let path = require('path');

let SRC = path.resolve(__dirname, 'src/main/js');
let DEST = path.resolve(__dirname, 'src/main/resources/static/app');




let config = {
    cache: true,
    entry: {
        main: [require.resolve('webpack/hot/only-dev-server'),
        'react-hot-loader/patch', 'whatwg-fetch', SRC]
    },
    resolve: {
        extensions: ['.js', '.jsx']
    },
    output: {
        path: DEST,
        filename: 'bundle.js',
        publicPath: 'http://localhost:9090/lts-ui/app/',
        library: 'TMS'
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                use: [
                    {
                        loader: 'eslint-loader',
                        options: {
                            emitWarning: true
                        }
                    }
                ],
                include: SRC,
                enforce: 'pre'
            }, {
                test: /\.jsx?$/,
                use: ['babel-loader'],
                include: SRC
            }, {
                test: /(\.css|\.scss)$/,
                include: SRC,
                use: [
                    {
                        loader: 'style-loader',
                        options: {
                            sourceMap: true
                        }
                    },
                    {
                        loader: 'css-loader',
                        options: {
                            sourceMap: true
                        }
                    }, {
                        loader: 'sass-loader',
                        options: {
                            sourceMap: true
                        }
                    }
                ]
            }, {
                test: /\.(jpe?g|png|gif|mp3)$/i,
                include: SRC,
                use: ['file-loader']
            }, {
                test: /\.ico$/,
                include: SRC,
                use: [
                    {
                        loader: 'file-loader',
                        options: {
                            name: '[name].[ext]'
                        }
                    }
                ]
            }, {
                test: /\.(woff|woff2|ttf|eot|svg)$/,
                include: SRC,
                use: [
                  {
                    loader: 'url-loader',
                    options: {
                      limit: 10000,
                      mimetype: 'application/octet-stream'
                    }
                  }
                ]
              }]
    },
    plugins: [
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': JSON.stringify('development')
            }
        })
        // new MockWebpackPlugin({
        //
        //     // mock config content
        //     config: mockConfig,
        //
        //     // the prot of the mock server
        //     port: 3000
        // })
    ],
    devServer: {
        port: 9090,
        proxy: {
            '/mock/': 'http://localhost:3000',
            '/**': {

                target: 'http://localhost:8080/lts-ui',

                secure: false,
                // node-http-proxy option - don't add /localhost:8080/ to proxied request paths
                prependPath: false
            }
        },
        publicPath: 'http://localhost:9090/lts-ui/app/'
    },
    devtool: 'inline-source-map',
    target: 'web'
};

module.exports = config;
