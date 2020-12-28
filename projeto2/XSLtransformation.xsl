<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>bookdepository.com</title>
            </head>
            <body>
                <div style="font-family:Arial">
                    <xsl:for-each select="AuthorCatalog/person">
                        <div style="font-size:15pt">
                            Author: <strong>
                                <xsl:value-of select="personname"/>
                            </strong>
                        </div>
                        <table border="2">
                            <tr bgcolor="#9acd32" style="font-size:13pt">
                                <th width="400">Title</th>
                                <th width="200">Rating</th>
                                <th width="200">Total ratings</th>
                                <th width="200">Best Seller Rank</th>
                                <th width="200">Publication Date</th>
                            </tr>

                            <xsl:for-each select="book">
                                <tr style="font-size:11pt">
                                    <td><xsl:value-of select="title"/></td>
                                    <td><xsl:value-of select="rating"/></td>
                                    <td><xsl:value-of select="totalRatings"/></td>
                                    <td><xsl:value-of select="bestSellerRank"/></td>
                                    <td><xsl:value-of select="date"/></td>
                                </tr>
                            </xsl:for-each>
                        </table>
                        <br/>
                    </xsl:for-each>
                    <br/>
                    <div style="font-size:18pt">
                        <strong>Author by Best Seller Rank
                            <xsl:value-of select="personname"/>
                        </strong>
                    </div>
                    <table border="2">
                        <tr bgcolor="#1E90FF" style="font-size:13pt">
                            <th width="200">Author</th>
                            <th width="200">Best Seller Rank</th>
                        </tr>

                        <xsl:for-each select="AuthorCatalog/author">
                            <tr style="font-size:11pt">
                                <td>
                                    <xsl:value-of select="authorname"/>
                                </td>
                                <td>
                                    <xsl:value-of select="rank"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>