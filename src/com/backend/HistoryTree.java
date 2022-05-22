package com.backend;

public class HistoryTree {
    private int current_node;
    private String main_text;
    private String first_option;
    private String second_option;
    private int first_child;
    private int second_child;
    private String image_url;

    private int next_node;

    public HistoryTree(){
        this.current_node = 0;
        this.main_text = "";
        this.first_option = "";
        this.second_option = "";
        this.first_child = 0;
        this.second_child = 0;
        this.image_url = "";
        this.next_node = 1;
    }

    public void update(int curnode, String main_text, String fopt, String sopt, int fchild, int schild, String iurl){
        this.current_node = curnode;
        this.main_text = main_text;
        this.first_option = fopt;
        this.second_option = sopt;
        this.first_child = fchild;
        this.second_child = schild;
        this.image_url = iurl;
    }

    public void setNodeToFirstChild(){
        this.next_node = this.first_child;
    }

    public void setNodeToSecondChild(){
        this.next_node = this.second_child;
    }

    public void setNextNode(int node){ this.next_node = node; }

    public int getCurrentNode(){
        return this.current_node;
    }

    public String getMainText(){
        return this.main_text;
    }

    public String getFirstOption() { return first_option; }

    public String getSecondOption() { return second_option; }

    public String getImageUrl() { return image_url; }

    public int getNextNode(){ return this.next_node; }
}
