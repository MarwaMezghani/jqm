/**
 * Copyright © 2013 enioka. All rights reserved
 * Authors: Marc-Antoine GOUILLART (marc-antoine.gouillart@enioka.com)
 *          Pierre COPPEE (pierre.coppee@enioka.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.enioka.jqm.jpamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.spi.ObjectFactory;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <strong>Not part of any API - this an internal JQM class and may change without notice.</strong> <br>
 * JPA persistence class for storing the JNDI object resources. This table is the actual JNDI directory back-end.
 */
@Entity
@Table(name = "JndiObjectResource")
public class JndiObjectResource implements Serializable
{
    private static final long serialVersionUID = 5387852232057745693L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 100, name = "name", unique = true)
    private String name;

    @Column(nullable = true, length = 20, name = "auth")
    private String auth = null;

    @Column(nullable = false, length = 100, name = "type")
    private String type;

    @Column(nullable = false, length = 100, name = "factory")
    private String factory;

    @Column(nullable = true, length = 250, name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "resource")
    private Collection<JndiObjectResourceParameter> parameters = new ArrayList<JndiObjectResourceParameter>();

    private Boolean singleton = false;

    /**
     * If true: loaded by the engine CL and cached. If not, loaded by payload CL and created on each lookup call.
     */
    public Boolean getSingleton()
    {
        return singleton;
    }

    /**
     * See {@link #getSingleton()}
     */
    public void setSingleton(Boolean singleton)
    {
        this.singleton = singleton;
    }

    /**
     * A technical ID without any meaning. Generated by the database.
     */
    public int getId()
    {
        return id;
    }

    void setId(final int id)
    {
        this.id = id;
    }

    /**
     * JNDI alias. JQM interprets all names as global aliases (no subcontexts). E.g.: jms/myqueueconnectionfactory.<br>
     * Max length is 100.
     */
    public String getName()
    {
        return name;
    }

    /**
     * See {@link #getName()}
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * Not used in JQM. Here for completion sake (possible values: Container, ?). JQM always assumes Container.
     */
    public String getAuth()
    {
        return auth;
    }

    /**
     * See {@link #getAuth()}
     */
    public void setAuth(final String auth)
    {
        this.auth = auth;
    }

    /**
     * Class name of the resource, i.e. the class that should be returned by a <code>lookup</code> call. E.g.:
     * <code>com.ibm.mq.jms.MQQueueConnectionFactory</code><br>
     * Max length is 100.
     */
    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    /**
     * Class name of the factory which will create the resource. It must implement {@link ObjectFactory} and respect the JNDI ObjectFactory
     * conventions.<br>
     * Max length is 100.
     */
    public String getFactory()
    {
        return factory;
    }

    /**
     * See {@link #getFactory()}
     */
    public void setFactory(final String factory)
    {
        this.factory = factory;
    }

    /**
     * A free text description. Max length is 250.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * See {@link #getDescription()}
     */
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * The parameters. These are specific to each resource type (i.e. each factory as specified inside {@link #getFactory()} has its own
     * parameter needs). E.g. for MQSeries: HOST, PORT, CHAN, TRAN, QMGR, ...
     */
    public Collection<JndiObjectResourceParameter> getParameters()
    {
        return parameters;
    }

    /**
     * See {@link #getParameters()}
     */
    public void setParameters(final Collection<JndiObjectResourceParameter> parameters)
    {
        this.parameters = parameters;
    }
}
