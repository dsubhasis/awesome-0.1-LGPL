const WORD_MAX = 10;
const RAND_DOC_LENGTH = 100;

$(document).ready(function(){

  $("#rarrow").click(function(){
    if(docIndex === docResults.length - 1)
      return;
    docIndex++;
    refreshDocuments();
  });

  $("#larrow").click(function(){
    if(docIndex === 0)
      return;
    docIndex--;
    refreshDocuments();
  });

  createChart();
  loadInitialData();
  updateHistogram({b: 4, a: 3});
});

function createiFrame(url){
  var frameWrapper = $("<div id='frame-wrapper'></div>")
    .click(removeiFrame)
    .appendTo("body");

  var defaultCannotLoad = $("<div></div>")
    .attr("id", "cannot-load")
    .addClass("frame")
    .addClass("cannot-load")
    .appendTo(frameWrapper)

  var loaderWrapper = $("<div>Loading...</div>")
    .addClass("loader-wrapper")
    .appendTo(defaultCannotLoad)

  var loader = $("<div></div>")
    .addClass("loader")
    .appendTo(loaderWrapper)

  var frame = $("<iframe id='frame' src=" + url + "></iframe>")
    .addClass("frame")
    .appendTo(frameWrapper);

  setTimeout(function(){
    createPopup(frameWrapper, url);
  }, 3000);
}

function createPopup(frameWrapper, url){
  var popup = $("<div></div>")
    .addClass("popup")
    .attr("title", url)
    .click(function(){
      window.open(url);
    })
    .appendTo(frameWrapper);

  //Create close button with bootstrap X icon.
  var closePopup = $("<button></button>")
    .addClass("close-button")
    .addClass("glyphicon glyphicon-remove")
    .attr("title", "Close Popup")
    .click(function(){
      event.stopPropagation();
      popup.remove()
    })
    .appendTo(popup);

  var popupText = $("<h5>If webpage doesn't load, click this popup to open the webpage in a new window</h5>")
    .attr("id", "popup-text")
    .appendTo(popup)
}

function removeiFrame(){
  $("#frame-wrapper").remove();
}


function color_generator(style,range,number) {
    var color = [];
    for(var i=0; i < number; i++){
        color.push(colorbrewer[style][range][i%range])
    }
    return color;
}

var histogramDataSet = {
    labels: [],
    datasets: [{
        label: 'words',
        backgroundColor: color_generator("Pastel2",8, WORD_MAX),
        //data: [{text: 'hi', size: 30}, {text: 'yo', size: 40}]
        data: []
    }]
};

var histogram;

function createChart(){
  histogram = new Chart('histogram', {
          responsive: true,
          maintainAspectRatio: false,
      type: 'horizontalBar',
      data: histogramDataSet,
      options: {
          // Elements options apply to all of the options unless overridden in a dataset
          // In this case, we are setting the border of each horizontal bar to be 2px wide
          elements: {
              rectangle: {
                  borderWidth: 2,
              }
          },
          events: ['mousemove','click'],
          responsive: true,
          maintainAspectRatio: false,
          legend: {
              display: false,
          },
          title: {
              display: true,
              text: 'Words Horizontal'
          },
          tooltips: {
              mode: 'point',
              intersect: true
          },
          hover:{
              mode: 'point',
              intersect: true
          },
          scales: {
              xAxes: [{
                  gridLines: {
                      display: true,
                  },
                  ticks: {
                      fontColor: "#CCC", // this here
                      beginAtZero : true
                  },
              }],
              yAxes: [{
                  //display: false,
                  gridLines: {
                      display: false,
                  },
                  ticks: {
                      fontColor : "#CCC", // this here
                  },
              }],
          },
          onClick : function (evt, item) {
              //var activePoints = myHorizontalBar.getElementsAtEvent(evt);
              if(item.length > 0)
              {
                  //get the internal index of slice in pie chart
                  var clickedElementindex = item[0]["_index"];
                  //get specific label by index
                  var bar_last_clicked = histogramDataSet.labels[clickedElementindex];
                  pushTerm(bar_last_clicked);
              }
          }
      }
  });
}

var filterWords = [];

function pushTerm(word){
  var index = filterWords.indexOf(word);
  if(index !== -1){
    $("#filterWord" + word).remove();
    filterWords.splice(index, 1);
    getAssociatedDocuments(word)
    .then(slowShowDocuments);

    return true;
  }
  filterWords.push(word);

  var wrapper = $("<div>").addClass('filterWordWrapper');
  var text = $("<span>").addClass('filterWordText').text(word);
  var closeButtton = $("<i>").addClass('fas fa-times wordClose');

  wrapper.attr('id', "filterWord" + word);
  wrapper.append(text);
  wrapper.append(closeButtton);
  wrapper.click(function(){
    var index = filterWords.indexOf(word);
    filterWords.splice(index, 1);
    $(this).remove();
    getAssociatedDocuments(word)
    .then(slowShowDocuments);
  });
  $("#histograph-filter-wrapper").append(wrapper);

  getAssociatedDocuments(word)
  .then(slowHideDocuments);
  return;
}

function slowShowDocuments(docList){
  var ret = '';
  $.each(docList, function(i, val){
    if(i)
      ret += ", ";
    ret += "#doc" + val;
  });
  $(ret).slideDown( "slow" );
}

function slowHideDocuments(docList){
  var ret = '';
  $.each(docList, function(i, val){
    if(i)
      ret += ", ";
    ret += "#doc" + val;
  });
  $(ret).slideUp( "slow" );
}

function updateHistogram(words){
  var labels = Object.keys(words).sort(function(a, b){
    return words[b] - words[a];
  });
  var data = [];
  $.each(labels, function(i, val){
    data.push(words[val]);
  });
  histogramDataSet.labels = labels;
  histogramDataSet.datasets[0].data = data;
  histogram.update();
}

function gibberish(text_len){
  if(!text_len)
    text_len = 40;
    var text = "";
    var possible = "ABCD EFGHIJ KLMNOPQR STUVWXYZ abcdefghij klmnopqr stuvwxyz abcdefghij klmnopqr stuvwxyz ";

    for(var i=0; i < text_len; i++)
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}

function getRandomDate(){
  var start = new Date(2018, 0, 1);
  var end = new Date(2018, 11, 31);
  return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
}

function getRandomDocument(){
  var doc = {};
  doc.date = getRandomDate();
  doc.subtext = gibberish(150);
  doc.docID = getRandomInt(RAND_DOC_LENGTH ** 4);

  if(Math.random() >= 0.5){
    doc.title = gibberish(15) + " Google"
    doc.url = "https://www.google.com";
  }else{
    doc.title = gibberish(15) + " SDSC"
    doc.url = "http://www.sdsc.edu";
  }

  return doc;
}

function getInitialData(){
  var initData = {
    histogram : {
      alfa : 10,
      bravo : 45,
      charlie : 39,
      delta : 8,
      echo : 46,
      frank : 8,
      golf : 11,
      hotel : 29
    },
    documents:[]
  };

  for(var i = 0; i < RAND_DOC_LENGTH; i++){
    initData.documents.push(getRandomDocument());
  }

  return Promise.resolve(initData);
}

var wordHash = [];
function getAssociatedDocuments(word){
  if(wordHash[word])
    return Promise.resolve(wordHash[word]);

  var ret = [];
  $.each(docResults, function(i, val){
    $.each(val, function(j, val){
      if(Math.random() < 0.6){
        ret.push(val.docID);
      }
    });
  });

  wordHash[word] = ret;

  return Promise.resolve(wordHash[word]);
}

function loadInitialData(){
  getInitialData()
  .then(function(initialData){
    initTopicGraph();
    updateHistogram(initialData.histogram);
    updateDocuments(initialData.documents);
 
  }).catch(console.log);
}

function daysInMonth (month, year) {
  return new Date(year, month, 0).getDate();
}

function monthFrom(d, months){
  if(!months && months !== 0)
    months = 1;
  d = new Date(d);

  var originalYear = d.getUTCFullYear();
  var originalMonth = d.getMonth();

  d.setDate(daysInMonth(originalMonth, originalYear));
  return d;
}

function updateDocuments(documents){
  documents.sort(function(a, b){
    return a.date - b.date;
  });

  var lastDateInSegment = monthFrom(documents[0].date);
  var index = 0;
  docResults = [[]];
  $.each(documents, function(i, d){
    if(d.date > lastDateInSegment){
      index++;
      docResults.push([]);
      lastDateInSegment = monthFrom(d.date);
    }
    docResults[index].push(d);
  });
  
  docIndex = 0;
  console.log(docIndex);
  refreshDocuments();
}

function pushDocument(d){
  var li = $("<li>").addClass('newspaper');
  var title = $("<h3>").addClass('std-margin newspaper-header').text(d.title);
  var dateLabel = $("<div>").addClass("std-margin newspaper-date").text(d.date.toLocaleDateString());
  var subtext = $("<p>").addClass('std-margin newspaper-content').text(d.subtext);

  li.attr('id', 'doc' + d.docID);

  li.click(function() {
    // createiFrame("http://www.google.com");
    createiFrame(d.url);
  });


  li.append(title);
  li.append(dateLabel);
  li.append(subtext);

  $("#newspaper-wrapper").append(li);
}

var docResults, docIndex;
function refreshDocuments(visibleDocuments){
  $("#newspaper-wrapper").empty();
  console.log(docIndex);
  var vDocuments = docResults[docIndex || 0];

  var startMonth = docResults[0][0].date, docIndex;
  var endMonth = monthFrom(docResults[0][0].date, docIndex + 1);

  var text = (startMonth.getMonth() + 1) + "/" + startMonth.getDate() + " - " + (endMonth.getMonth() + 1) + "/" + endMonth.getDate();
  $("#dateRangeText").text(text);

  $.each(vDocuments, function(i, d){
    if(!visibleDocuments || visibleDocuments.indexOf(d.docID) !== -1)
      pushDocument(d);
  });

  if(docIndex === 0){
    $("#larrow").addClass('disabledButton');
  }else{
    $("#larrow").removeClass('disabledButton');
  }

  if(docIndex === docResults.length - 1){
    $("#rarrow").addClass('disabledButton');
  }else{
    $("#rarrow").removeClass('disabledButton');
  }
}



/*
 *   TOPIC GRAPH HANDLERS
 */

//data format to be later specified
function setTopicGraphData(data){

}

function initTopicGraph() {
  var neo4jd3 = new Neo4jd3('#topic-graph', {
      highlight: [
          {
              class: 'Project',
              property: 'name',
              value: 'neo4jd3'
          }, {
              class: 'User',
              property: 'userId',
              value: 'eisman'
          }
      ],
      icons: {
//                        'Address': 'home',
          'Api': 'gear',
//                        'BirthDate': 'birthday-cake',
          'Cookie': 'paw',
//                        'CreditCard': 'credit-card',
//                        'Device': 'laptop',
          'Email': 'at',
          'Git': 'git',
          'Github': 'github',
          'Google': 'google',
//                        'icons': 'font-awesome',
          'Ip': 'map-marker',
          'Issues': 'exclamation-circle',
          'Language': 'language',
          'Options': 'sliders',
          'Password': 'lock',
          'Phone': 'phone',
          'Project': 'folder-open',
          'SecurityChallengeAnswer': 'commenting',
          'User': 'user',
          'zoomFit': 'arrows-alt',
          'zoomIn': 'search-plus',
          'zoomOut': 'search-minus'
      },
      images: {
          'Address': 'img/twemoji/1f3e0.svg',
//                        'Api': 'img/twemoji/1f527.svg',
          'BirthDate': 'img/twemoji/1f382.svg',
          'Cookie': 'img/twemoji/1f36a.svg',
          'CreditCard': 'img/twemoji/1f4b3.svg',
          'Device': 'img/twemoji/1f4bb.svg',
          'Email': 'img/twemoji/2709.svg',
          'Git': 'img/twemoji/1f5c3.svg',
          'Github': 'img/twemoji/1f5c4.svg',
          'icons': 'img/twemoji/1f38f.svg',
          'Ip': 'img/twemoji/1f4cd.svg',
          'Issues': 'img/twemoji/1f4a9.svg',
          'Language': 'img/twemoji/1f1f1-1f1f7.svg',
          'Options': 'img/twemoji/2699.svg',
          'Password': 'img/twemoji/1f511.svg',
//                        'Phone': 'img/twemoji/1f4de.svg',
          'Project': 'img/twemoji/2198.svg',
          'Project|name|neo4jd3': 'img/twemoji/2196.svg',
//                        'SecurityChallengeAnswer': 'img/twemoji/1f4ac.svg',
          'User': 'img/twemoji/1f600.svg'
//                        'zoomFit': 'img/twemoji/2194.svg',
//                        'zoomIn': 'img/twemoji/1f50d.svg',
//                        'zoomOut': 'img/twemoji/1f50e.svg'
      },
      minCollision: 60,
      neo4jDataUrl: 'test-data/neo4jData.js',
      nodeRadius: 25,
      onNodeDoubleClick: function(node) {
          switch(node.id) {
              case '25':
                  // Google
                  window.open(node.properties.url, '_blank');
                  break;
              default:
                  var maxNodes = 5,
                      data = neo4jd3.randomD3Data(node, maxNodes);
                  neo4jd3.updateWithD3Data(data);
                  break;
          }
      },
      onRelationshipDoubleClick: function(relationship) {
          console.log('double click on relationship: ' + JSON.stringify(relationship));
      },
      zoomFit: true
  });
}


//open to other interpretations.
//This is just the handler for if the document list needs to be filtered
function onTopicClicked(topicName, docIDList){

}

