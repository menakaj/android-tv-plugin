/*
* Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.homeautomation.androidtv.api;

import io.swagger.annotations.*;
import org.homeautomation.androidtv.api.constants.AndroidTVConstants;
import org.wso2.carbon.apimgt.annotations.api.Scope;
import org.wso2.carbon.apimgt.annotations.api.Scopes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


/**
 * This is the API which is used to control and manage device type functionality
 */
@SwaggerDefinition(
        info = @Info(
                version = "1.0.0",
                title = "",
                extensions = {
                        @Extension(properties = {
                                @ExtensionProperty(name = "name", value = "androidtv"),
                                @ExtensionProperty(name = "context", value = "/androidtv"),
                        })
                }
        ),
        tags = {
                @Tag(name = "androidtv", description = "")
        }
)
@Scopes(
        scopes = {
                @Scope(
                        name = "Enroll device",
                        description = "",
                        key = "perm:androidtv:enroll",
                        permissions = {"/device-mgt/devices/enroll/androidtv"}
                )
        }
)
@SuppressWarnings("NonJaxWsWebServices")
public interface DeviceTypeService {
    /**
     * End point to send the key words to the device
     *
     * @param deviceId The registered device Id.
     */
    @POST
    @Path("device/{deviceId}/volume")
    @ApiOperation(
            httpMethod = "POST",
            value = "End point to mute the android TV",
            notes = "",
            tags = "androidtv",
            extensions = {
                    @Extension(properties = {
                            @ExtensionProperty(name = AndroidTVConstants.SCOPE, value = "perm:androidtv:enroll")
                    })
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "OK.",
                    response = Response.class,
                    responseHeaders = {
                            @ResponseHeader(
                                    name = "Content-Type",
                                    description = "The content type of the body"),
                            @ResponseHeader(
                                    name = "Last-Modified",
                                    description = "Date and time the resource was last modified.\n" +
                                            "Used by caches, or in conditional requests."),
                    }),
            @ApiResponse(
                    code = 400,
                    message = "Bad Request. \n Invalid Device Identifiers found.",
                    response = Response.class),
            @ApiResponse(
                    code = 401,
                    message = "Unauthorized. \n Unauthorized request."),
            @ApiResponse(
                    code = 500,
                    message = "Internal Server Error. \n Error occurred while executing command operation to"
                            + " send keywords",
                    response = Response.class)
    })
    Response toggleVolume(
            @ApiParam(
                    name = "deviceId",
                    value = "The registered device Id.",
                    required = true)
            @PathParam("deviceId") String deviceId,
            @ApiParam(
                    name = "state",
                    value = "The state words to be sent. (Mute/ UnMute)",
                    required = true)
            @QueryParam("state") String state);

    /**
     * End point to disable the HDMI in Android TV device.
     */
    @POST
    @Path("device/{deviceId}/hdmi")
    @ApiOperation(
            httpMethod = "POST",
            value = "Disable HDMI in Android tv",
            notes = "",
            response = Response.class,
            tags = "androidtv",
            extensions = {
                    @Extension(properties = {
                            @ExtensionProperty(name = AndroidTVConstants.SCOPE, value = "perm:androidtv:enroll")
                    })
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "OK.",
                    response = Response.class,
                    responseHeaders = {
                            @ResponseHeader(
                                    name = "Content-Type",
                                    description = "The content type of the body"),
                            @ResponseHeader(
                                    name = "Last-Modified",
                                    description = "Date and time the resource was last modified.\n" +
                                            "Used by caches, or in conditional requests."),
                    }),
            @ApiResponse(
                    code = 400,
                    message = "Bad Request. \n Invalid Device Identifiers found.",
                    response = Response.class),
            @ApiResponse(
                    code = 401,
                    message = "Unauthorized. \n Unauthorized request."),
            @ApiResponse(
                    code = 500,
                    message = "Internal Server Error. \n Error occurred while executing command operation to"
                            + " send threashold",
                    response = Response.class)
    })
    Response toggleHDMI(
            @ApiParam(
                    name = "deviceId",
                    value = "The registered device Id.",
                    required = true)
            @PathParam("deviceId") String deviceId,
            @ApiParam(
                    name = "state",
                    value = "The state to be sent. (Enable/ Disable)",
                    required = true)
            @QueryParam("state") String state);

    /**
     * Enroll devices.
     */
    @POST
    @Path("device/{device_id}/register")
    @ApiOperation(
            httpMethod = "POST",
            value = "Enroll device",
            notes = "",
            response = Response.class,
            tags = "androidtv",
            extensions = {
                    @Extension(properties = {
                            @ExtensionProperty(name = AndroidTVConstants.SCOPE, value = "perm:androidtv:enroll")
                    })
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 202,
                    message = "Accepted.",
                    response = Response.class),
            @ApiResponse(
                    code = 406,
                    message = "Not Acceptable"),
            @ApiResponse(
                    code = 500,
                    message = "Internal Server Error. \n Error on retrieving stats",
                    response = Response.class)
    })
    Response register(
            @ApiParam(
                    name = "deviceId",
                    value = "Device identifier id of the device to be added",
                    required = true)
            @PathParam("device_id") String deviceId,
            @ApiParam(
                    name = "deviceName",
                    value = "Device name of the device to be added",
                    required = true)
            @QueryParam("deviceName") String deviceName);

}