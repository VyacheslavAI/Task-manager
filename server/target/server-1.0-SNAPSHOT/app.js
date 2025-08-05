var store = Ext.create('Ext.data.Store', {
    data: [{
        'name': 'Lisa',
        "email": "lisa@simpsons.com",
        "phone": "555-111-1224"
    }, {
        'name': 'Bart',
        "email": "bart@simpsons.com",
        "phone": "555-222-1234"
    }, {
        'name': 'Homer',
        "email": "home@simpsons.com",
        "phone": "555-222-1244"
    }]
});

Ext.create('Ext.grid.Grid', {
    title: 'Simpsons',

    store: store,

    columns: [{
        text: 'Name',
        dataIndex: 'name',
        flex: 1
    }, {
        text: 'Email',
        dataIndex: 'email',
        flex: 1
    }, {
        text: 'Phone',
        dataIndex: 'phone',
        width: 120
    }],

    height: 300,
    width: 500,
    renderTo: Ext.getBody()
});