let webpack = require('webpack');
let ExtractTextPlugin = require('extract-text-webpack-plugin');
let path = require('path');

let SRC = path.resolve(__dirname, 'src/main/js');
let DEST = path.resolve(__dirname, 'src/main/resources/static/app');

const extractPlugin = new ExtractTextPlugin({
    filename: 'bundle.css',
    disable: false,
    allChunks: true
});

let config = {
    cache: true,
    entry: ['whatwg-fetch', SRC],
    target: 'web',
    resolve: {
        extensions: ['.js', '.jsx']
    },
    output: {
        path: DEST,
        filename: 'bundle.js',
        publicPath: '/claims/app/',
        library: 'LTS'
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
                loaders: ['babel-loader'],
                include: SRC
            }, {
                test: /(\.css|\.scss)$/,
                include: SRC,
                use: extractPlugin.extract({
                    use: [
                        {
                            loader: 'css-loader',
                            options: {
                                sourceMap: true
                            }
                        },
                        {
                            loader: 'postcss-loader',
                            options: {
                                sourceMap: true
                            }
                        },
                        {
                            loader: 'sass-loader',
                            options: {
                                sourceMap: true
                            }
                        }
                    ],
                    fallback: 'style-loader'
                })
            }, {
                test: /\.(jpe?g|png|gif|mp3)$/i,
                include: SRC,
                loaders: ['file-loader']
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
        extractPlugin,
        new webpack.LoaderOptionsPlugin({
            options: {
                context: __dirname,
                debug: true
            }
        }),
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': JSON.stringify('production')
            }
        }),
        new webpack.optimize.UglifyJsPlugin({
            minimize: true
        })
    ]
};
module.exports = config;
