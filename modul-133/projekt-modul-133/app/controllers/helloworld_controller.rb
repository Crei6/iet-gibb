class HelloworldController < ApplicationController
  def index
    i = 1
    @output = ""
    while i < 100 do
      @output += i.to_s + "<br>"
      i += 1
    end
    @output += "Ich komme..."
  end
end
