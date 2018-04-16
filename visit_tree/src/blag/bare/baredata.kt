package blag.bare

interface Tree {}

class Node(public vararg val children: Tree): Tree {}

class Leaf(): Tree {}
